package com.example.template.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    /**
     * S3 bucket 파일 읽어 DB에 저장
     */
    @Transactional
    public void readObject() throws IOException {
        S3Object o = amazonS3.getObject(new GetObjectRequest(bucket, "UGGTHON DATA.csv"));
        S3ObjectInputStream ois = null;
        BufferedReader br = null;

        // Read the CSV one line at a time and process it.
        try {
            ois = o.getObjectContent();
            System.out.println ("ois = " + ois);
            br = new BufferedReader (new InputStreamReader(ois, "UTF-8"));

            String line;

            while ((line = br.readLine()) != null) {
                // Store 1 record in an array separated by commas

                log.info("line = {}", line);

                String[] data = line.split(",", 0);

                log.info("data = {}", Arrays.toString(data));
            }
        } finally {
            if(ois != null){
                ois.close();
            }
            if(br != null){
                br.close();
            }
        }
    }
}
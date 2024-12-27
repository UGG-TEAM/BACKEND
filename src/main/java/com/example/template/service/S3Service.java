package com.example.template.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    /**
     * S3에서 CSV 파일을 읽어 데이터베이스에 저장
     *
     * @throws IOException 파일 읽기 실패 시 예외 발생
     * @throws CsvValidationException CSV 파일 형식이 유효하지 않을 경우 발생
     */
    @Transactional
    public void readObject() throws IOException, CsvValidationException {
        // S3에서 지정된 파일 가져오기
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket, "UGGTHON DATA.csv"));

        // try-with-resources로 자원 자동 관리
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(s3Object.getObjectContent(), "UTF-8"));
             CSVReader csvReader = new CSVReader(bufferedReader)) {

            List<String[]> rows = new ArrayList<>();
            String[] row;

            // CSV 데이터를 읽어 처리
            while ((row = csvReader.readNext()) != null) {
                log.info("row = {}", Arrays.toString(row));
                rows.add(row);
            }

            // 필요한 추가 처리(예: 데이터 저장) 수행
            processCsvData(rows);

        } catch (IOException | CsvValidationException e) {
            log.error("Failed to process CSV file from S3: {}", e.getMessage(), e);
            throw e; // 예외를 다시 던져 호출자에게 알림
        }
    }

    /**
     * CSV 데이터를 처리 (예: 데이터베이스에 저장)
     *
     * @param rows 읽어온 CSV 데이터
     */
    private void processCsvData(List<String[]> rows) {
        // TODO: 데이터 저장 로직 구현
        log.info("Processing {} rows of data", rows.size());
    }
}

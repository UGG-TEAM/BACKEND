package com.example.template.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.example.template.entity.Program;
import com.example.template.repository.ProgramRepository;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;
    private final ProgramRepository programRepository;

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

        log.info("파일을 가져옵니다");
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket, "UGGTHON DATA.csv"));
        log.info("파일 가져오기 성공");

        // try-with-resources로 자원 자동 관리
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(s3Object.getObjectContent(), "UTF-8"));
             CSVReader csvReader = new CSVReader(bufferedReader)) {

            List<String[]> rows = new ArrayList<>();
            String[] row;
            csvReader.readNext();

            log.info("데이터를 읽습니다.");

            // CSV 데이터를 읽어 처리
            while ((row = csvReader.readNext()) != null) {
                List<String> values = Arrays.stream(row).toList();
                Program program = Program.builder()
                        .title(values.get(0))
                        .postDate(values.get(1))  // 만약 values.get(1) 값이 이미 LocalDate 형식의 문자열이라면
                        .isOnce(values.get(2))  // values.get(2)가 boolean 형태로 제대로 되어 있다면
                        .region(values.get(3))  // Region 열거형 값을 직접 받는다
                        .category(values.get(4))  // Category 열거형 값을 직접 받는다
                        .targetType(values.get(5))  // TargetType 열거형 값을 직접 받는다
                        .programType(values.get(6))  // ProgramType 열거형 값을 직접 받는다
                        .hostType(values.get(7))  // HostType 열거형 값을 직접 받는다
                        .target(values.get(8))
                        .content(values.get(9))
                        .minAge(values.get(10))  // 값이 int로 되어있다면, Integer.parseInt()로 파싱
                        .maxAge(values.get(11))  // 값이 int로 되어있다면, Integer.parseInt()로 파싱
                        .cost(values.get(12))  // 값이 int로 되어있다면, Integer.parseInt()로 파싱
                        .location(values.get(13))
                        .homePage(values.get(14))
                        .host(values.get(15))
                        .manager(values.get(16))
                        .managerEmail(values.get(17))
                        .managerPhoneNumber(values.get(18))
                        .sponsor(values.get(19))
                        .startDate(values.get(20))  // LocalDate.parse()가 필요 없다면
                        .endDate(values.get(21))  // LocalDate.parse()가 필요 없다면
                        .build();
                programRepository.save(program);
            }

        } catch (IOException | CsvValidationException e) {
            log.error("Failed to process CSV file from S3: {}", e.getMessage(), e);
            throw e; // 예외를 다시 던져 호출자에게 알림
        }
    }
}

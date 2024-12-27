package com.example.template;

import com.example.template.entity.Member;
import com.example.template.repository.MemberRepository;
import com.example.template.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class S3CsvToDatabaseRunner implements CommandLineRunner {

    private final S3Service s3Service;
    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {

        s3Service.readObject();

        Member member = Member.builder()
                .id(1L)
                .build();

        memberRepository.save(member);

        System.out.println("S3에서 파일을 읽어 데이터베이스에 저장하였습니다.");
    }
}

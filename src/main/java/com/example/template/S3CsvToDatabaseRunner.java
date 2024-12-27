package com.example.template;

import com.example.template.entity.CheckList;
import com.example.template.entity.Member;
import com.example.template.repository.CheckListRepository;
import com.example.template.repository.MemberRepository;
import com.example.template.repository.ProgramRepository;
import com.example.template.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.MonthDay;

@Component
@RequiredArgsConstructor
public class S3CsvToDatabaseRunner implements CommandLineRunner {

    private final S3Service s3Service;
    private final MemberRepository memberRepository;
    private final ProgramRepository programRepository;
    private final CheckListRepository checkListRepository;

    @Override
    public void run(String... args) throws Exception {

        s3Service.readObject();

        MonthDay monthDay1 = MonthDay.of(12, 27);
        MonthDay monthDay2 = MonthDay.of(12, 28);

        Member member = Member.builder()
                .id(1L)
                .build();

        CheckList checkList1 = CheckList
                .builder()
                .isCheck(true)
                .member(member)
                .program(programRepository.findById(1L))
                .finishDate(monthDay1)
                .build();

        CheckList checkList2 = CheckList
                .builder()
                .isCheck(true)
                .member(member)
                .program(programRepository.findById(2L))
                .finishDate(monthDay1)
                .build();

        CheckList checkList3 = CheckList
                .builder()
                .isCheck(true)
                .member(member)
                .program(programRepository.findById(3L))
                .finishDate(monthDay1)
                .build();

        CheckList checkList4 = CheckList
                .builder()
                .isCheck(false)
                .member(member)
                .program(programRepository.findById(4L))
                .build();

        CheckList checkList5 = CheckList
                .builder()
                .isCheck(false)
                .member(member)
                .program(programRepository.findById(5L))
                .build();

        memberRepository.save(member);
        checkListRepository.save(checkList1);
        checkListRepository.save(checkList2);
        checkListRepository.save(checkList3);
        checkListRepository.save(checkList4);
        checkListRepository.save(checkList5);

        System.out.println("S3에서 파일을 읽어 데이터베이스에 저장하였습니다.");
    }
}

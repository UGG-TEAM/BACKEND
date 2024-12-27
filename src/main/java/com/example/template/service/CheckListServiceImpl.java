package com.example.template.service;

import com.example.template.common.exception.handler.GeneralHandler;
import com.example.template.common.response.status.ErrorCode;
import com.example.template.dto.CheckListCompleteResponseDTO;
import com.example.template.dto.CheckListDTO;
import com.example.template.entity.CheckList;
import com.example.template.entity.Member;
import com.example.template.entity.Program;
import com.example.template.repository.CheckListRepository;
import com.example.template.repository.MemberRepository;
import com.example.template.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckListServiceImpl implements CheckListService {

    final MemberRepository memberRepository;
    final CheckListRepository checkListRepository;
    final ProgramRepository programRepository;

    @Override
    public List<CheckListDTO> getCheckList(Long memberId, boolean isChecked) {
        Member member = (Member) memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralHandler(ErrorCode.MEMBER_NOT_FOUND));

        List<CheckList> checkLists = checkListRepository.findAllByMemberAndIsCheck(member, isChecked);

        return checkLists.stream()
                .map(checkList -> toCheckListDto(checkList))
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckListDTO> getAllCheckList(Long memberId) {
        Member member = (Member) memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralHandler(ErrorCode.MEMBER_NOT_FOUND));

        List<CheckList> checkLists = checkListRepository.findAllByMember(member);

        return checkLists.stream()
                .map(checkList -> toCheckListDto(checkList))
                .collect(Collectors.toList());
    }

    @Override
    public void addCheckList(Long memberId, Long programId) {
        Member member = (Member) memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralHandler(ErrorCode.MEMBER_NOT_FOUND));

        Program program = (Program) programRepository.findById(programId).orElseThrow(()->new GeneralHandler(ErrorCode.MEMBER_NOT_FOUND));

        CheckList checkList = CheckList.builder()
                .program(program)
                .member(member)
                .isCheck(false)
                .build();

        checkListRepository.save(checkList);

        return;
    }

    @Override
    public CheckListCompleteResponseDTO completeCheckList(Long checkListId) {

        CheckList checkList = (CheckList) checkListRepository.findById(checkListId).orElseThrow(()->new GeneralHandler(ErrorCode._BAD_REQUEST));
        checkList.setCheck(true);
        checkList.setFinishDate(MonthDay.from(LocalDate.now()));
        checkListRepository.save(checkList);

        return toCheckListCompleteResponseDTO(checkList);
    }

    public List<CheckListDTO> getDateCheckList(MonthDay monthDay) {

        List<CheckList> checkLists = checkListRepository.findAllByFinishDate(monthDay);

        return checkLists.stream()
                .map(this::toCheckListDto) // 변환 메서드 호출
                .collect(Collectors.toList()); // 리스트로 반환
    }

    public CheckListDTO toCheckListDto(CheckList checkList){
        return CheckListDTO.builder()
                .id(checkList.getId())
                .programId(checkList.getProgram().getId())
                .programName(checkList.getProgram().getTitle())
                .content(checkList.getProgram().getContent())
                .date(String.valueOf(checkList.getFinishDate()))
                .isChecked(checkList.isCheck())
                .build();
    }

    private CheckListCompleteResponseDTO toCheckListCompleteResponseDTO(CheckList checkList){
        return CheckListCompleteResponseDTO.builder()
                .checklistDate(checkList.getFinishDate())
                .checklistId(checkList.getId())
                .build();
    }
}

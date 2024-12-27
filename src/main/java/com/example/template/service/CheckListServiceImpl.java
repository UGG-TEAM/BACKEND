package com.example.template.service;

import com.example.template.common.exception.handler.GeneralHandler;
import com.example.template.common.response.status.ErrorCode;
import com.example.template.dto.CheckListDTO;
import com.example.template.entity.CheckList;
import com.example.template.entity.Member;
import com.example.template.entity.Program;
import com.example.template.repository.CheckListRepository;
import com.example.template.repository.MemberRepository;
import com.example.template.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void completeCheckList(Long checkListId) {

        CheckList checkList = (CheckList) checkListRepository.findById(checkListId).orElseThrow(()->new GeneralHandler(ErrorCode._BAD_REQUEST));
        checkList.setCheck(true);
        checkListRepository.save(checkList);

        return;
    }

    public CheckListDTO toCheckListDto(CheckList checkList){
        return CheckListDTO.builder()
                .id(checkList.getId())
                .programId(checkList.getProgram().getId())
                .programName(checkList.getProgram().getTitle())
                .content(checkList.getProgram().getContent())
                .isChecked(checkList.isCheck())
                .build();
    }
}

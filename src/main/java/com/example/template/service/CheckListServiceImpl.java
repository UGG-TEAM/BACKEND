package com.example.template.service;

import com.example.template.common.exception.handler.GeneralHandler;
import com.example.template.common.response.status.ErrorCode;
import com.example.template.dto.CheckListDTO;
import com.example.template.entity.CheckList;
import com.example.template.entity.Member;
import com.example.template.repository.CheckListRepository;
import com.example.template.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckListServiceImpl implements CheckListService {

    final MemberRepository memberRepository;
    final CheckListRepository checkListRepository;

    @Override
    public List<CheckListDTO> getCheckList(Long memberId, boolean isChecked) {
        Member member = (Member) memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralHandler(ErrorCode.MEMBER_NOT_FOUND));

        List<CheckList> checkLists = checkListRepository.findAllByMemberAndIsCheck(member, false);

        return checkLists.stream()
                .map(checkList -> toCheckListDto(checkList))
                .collect(Collectors.toList());
    }

    public CheckListDTO toCheckListDto(CheckList checkList){
        return CheckListDTO.builder()
                .id(checkList.getId())
                .programName(checkList.getProgram().getTitle())
                .content(checkList.getProgram().getContent())
                .isChecked(checkList.isCheck())
                .build();
    }
}

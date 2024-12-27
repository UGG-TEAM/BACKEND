package com.example.template.service;

import com.example.template.dto.CheckListCompleteResponseDTO;
import com.example.template.dto.CheckListDTO;

import java.time.MonthDay;
import java.util.List;

public interface CheckListService {

    List<CheckListDTO> getCheckList(Long memberId, boolean isChecked);

    void addCheckList(Long memberId, Long programId);

    CheckListCompleteResponseDTO completeCheckList(Long checkListId);

    List<CheckListDTO> getAllCheckList(Long memberId);

    List<CheckListDTO> getDateCheckList(MonthDay monthDay);
}

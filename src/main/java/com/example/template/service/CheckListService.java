package com.example.template.service;

import com.example.template.dto.CheckListDTO;

import java.util.List;

public interface CheckListService {

    List<CheckListDTO> getCheckList(Long memberId, boolean isChecked);
}

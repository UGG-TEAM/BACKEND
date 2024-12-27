package com.example.template.controller;

import com.example.template.common.response.ApiResponse;
import com.example.template.common.response.status.SuccessCode;
import com.example.template.dto.CheckListDTO;
import com.example.template.entity.CheckList;
import com.example.template.entity.Member;
import com.example.template.service.CheckListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "체크리스트 관련 API", description = "체크리스트 관련 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/checklist")
public class CheckListController {

    final CheckListService checkListService;

    @GetMapping("checklist")
    @Operation(summary = "미완료 체크리스트입니다.")
    public ApiResponse<List<CheckListDTO>> getInCompleteCheckList(@RequestParam boolean isChecked) {
        List<CheckListDTO> checkListDTOList = checkListService.getCheckList(1L, isChecked);
        return ApiResponse.onSuccess(checkListDTOList);
    }

    @PostMapping("checklistadd")
    @Operation(summary = "체크리스트 추가입니다.")
    public ApiResponse<?> addCheckList(@RequestParam Long memberId, @RequestParam Long programId) {
        checkListService.addCheckList(1L, programId);
        return ApiResponse.onSuccess(null);
    }

    @PostMapping("checklistcomplete")
    @Operation(summary = "체크리스트 완료입니다.")
    public ApiResponse<?> completeCheckList(@RequestParam Long checkListId) {
        checkListService.completeCheckList(checkListId);
        return ApiResponse.onSuccess(null);
    }
}

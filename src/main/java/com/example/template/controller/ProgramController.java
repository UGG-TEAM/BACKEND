package com.example.template.controller;

import com.example.template.common.response.ApiResponse;
import com.example.template.common.response.status.SuccessCode;
import com.example.template.dto.HomeProgramDTO;
import com.example.template.service.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "정책 관련 API", description = "정책 관련 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/program")
public class ProgramController {

    private final ProgramService programService;

    @GetMapping("/home")
    @Operation(summary = "홈 화면에 불러올 사업들입니다.")
    public ApiResponse<List<HomeProgramDTO>> createCastByKeyword(@RequestParam int page) {
        return ApiResponse.of(SuccessCode._OK, programService.getHomePrograms(page));
    }

}

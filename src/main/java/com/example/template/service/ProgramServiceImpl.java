package com.example.template.service;

import com.example.template.common.exception.GeneralException;
import com.example.template.common.exception.handler.GeneralHandler;
import com.example.template.common.response.status.ErrorCode;
import com.example.template.dto.HomeProgramDTO;
import com.example.template.dto.RecommendProgramDTO;
import com.example.template.entity.Program;
import com.example.template.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    public List<HomeProgramDTO> getHomePrograms(int page) {

        Pageable pageable = PageRequest.of(page, 5);

        return toHomeProgramDTO(programRepository.findAllByOrderByPostDateDesc(pageable).getContent());
    }

    public List<RecommendProgramDTO> getRecommendPrograms(String category) {
        Pageable pageable = PageRequest.of(0, 5);

        return toRecommendProgramDTO(programRepository.findAllByCategoryContains(category, pageable).getContent());
    }

    public String getComment(String type){
        return switch (type) {
            case "은둔형 고립군" -> null;
            case "좌절형 고립군" -> null;
            case "관계단절형 고립군" -> null;
            case "의존형 고립군" -> null;
            default -> throw new GeneralHandler(ErrorCode._BAD_REQUEST);
        };
    }

    private List<HomeProgramDTO> toHomeProgramDTO(List<Program> programs) {

        log.info("program 데이터베이스에서 조회 : ", programs.size());

        return programs.stream()
                .map(program -> HomeProgramDTO.builder()
                        .title(program.getTitle())
                        .region(program.getRegion())
                        .category(parse(program.getCategory()))
                        .target(parse(program.getTargetType()))
                        .type(parse(program.getProgramType()))
                        .content(program.getContent())
                        .build())
                .toList();
    }

    private List<RecommendProgramDTO> toRecommendProgramDTO(List<Program> programs) {

        log.info("추천 program 데이터베이스에서 조회 : ", programs.size());

        return programs.stream()
                .map(program -> RecommendProgramDTO.builder()
                        .title(program.getTitle())
                        .content(program.getContent())
                        .build())
                .toList();
    }

    private String parse(String input) {
        if (input == null || input.isEmpty()) {
            return ""; // 빈 문자열이나 null 처리
        }
        int commaIndex = input.indexOf(',');
        // 쉼표가 없으면 전체 문자열을 반환
        if (commaIndex == -1) {
            return input;
        }
        // 쉼표 이전까지의 부분 문자열을 반환
        return input.substring(0, commaIndex);
    }
}

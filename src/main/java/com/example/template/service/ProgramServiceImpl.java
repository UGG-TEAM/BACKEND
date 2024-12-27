package com.example.template.service;

import com.example.template.dto.HomeProgramDTO;
import com.example.template.entity.Program;
import com.example.template.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    public List<HomeProgramDTO> getHomePrograms() {

        Pageable pageable = PageRequest.of(0, 5);

        return toHomeProgramDTO(programRepository.findAllByOrderByPostDateDesc(pageable).getContent());
    }

    private List<HomeProgramDTO> toHomeProgramDTO(List<Program> programs) {

        log.info("program 데이터베이스에서 조회 : ", programs.size());

        return programs.stream()
                .map(program -> HomeProgramDTO.builder()
                        .title(program.getTitle())
                        .region(program.getRegion())
                        .category(program.getCategory())
                        .target(program.getTargetType())
                        .type(program.getProgramType())
                        .date(program.getPostDate())
                        .build())
                .toList();
    }
}

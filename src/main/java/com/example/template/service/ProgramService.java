package com.example.template.service;

import com.example.template.dto.HomeProgramDTO;
import com.example.template.dto.RecommendProgramDTO;
import com.example.template.dto.RecommendProgramRequestDTO;
import com.example.template.dto.RecommendProgramResponseDTO;

import java.util.List;

public interface ProgramService {
    List<HomeProgramDTO> getHomePrograms(int page);
    RecommendProgramResponseDTO getRecommendPrograms(RecommendProgramRequestDTO recommendProgramRequestDTO);
}

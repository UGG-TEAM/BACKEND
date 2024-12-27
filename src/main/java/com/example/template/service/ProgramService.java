package com.example.template.service;

import com.example.template.dto.HomeProgramDTO;
import com.example.template.dto.RecommendProgramDTO;

import java.util.List;

public interface ProgramService {
    List<HomeProgramDTO> getHomePrograms(int page);
    List<RecommendProgramDTO> getRecommendPrograms(String category);
    String getComment(String type);
}

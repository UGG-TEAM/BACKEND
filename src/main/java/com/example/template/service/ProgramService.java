package com.example.template.service;

import com.example.template.dto.HomeProgramDTO;

import java.util.List;

public interface ProgramService {
    List<HomeProgramDTO> getHomePrograms(int page);
}

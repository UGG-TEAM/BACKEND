package com.example.template.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendProgramResponseDTO {
    List<RecommendProgramDTO> recommendProgramDTOList;
    List<String> comment;
}

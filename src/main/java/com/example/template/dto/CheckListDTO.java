package com.example.template.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckListDTO {

    private Long id;
    private String programName;
    private String content;
    private boolean isChecked;

}

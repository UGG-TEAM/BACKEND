package com.example.template.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.MonthDay;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckListCompleteResponseDTO {
    long checklistId;
    MonthDay checklistDate;
}

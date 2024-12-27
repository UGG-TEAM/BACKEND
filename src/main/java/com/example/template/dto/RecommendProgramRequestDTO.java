package com.example.template.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendProgramRequestDTO {

    int id;
    String mostNeeded;
    String neededArea;
    String address;
    String program;
    String period;
    String currentActivity;
    String activityType;
    String education;
    String expect;
    String addtionalSupport;
    String expectedCategory;
    String expectedType;
}

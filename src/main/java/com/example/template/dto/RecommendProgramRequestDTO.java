package com.example.template.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendProgramRequestDTO {

    int user_id; // user_id -> int
    String most_needed; // most_needed -> String
    String needed_help_field; // needed_help_field -> String
    String residence_area; // residence_area -> String
    String program_method; // program_method -> String
    String participation_period; // participation_period -> String
    String current_activity; // current_activity -> String
    String activity_type; // activity_type -> String
    String learning_method; // learning_method -> String
    String expected_result; // expected_result -> String
    String additional_support; // additional_support -> String
}

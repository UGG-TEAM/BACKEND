package com.example.template.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomeProgramDTO {
    String title;
    String region;
    String category;
    String target;
    String type;
    String content;
    String website;
}

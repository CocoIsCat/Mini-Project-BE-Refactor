package com.example.finance7.member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SurveyRequestDTO {

    private String[] tags;
}
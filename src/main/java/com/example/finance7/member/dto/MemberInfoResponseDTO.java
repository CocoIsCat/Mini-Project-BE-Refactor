package com.example.finance7.member.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class MemberInfoResponseDTO extends StatusResponseDTO {

    private String email;
    private String name;
    private String birthday;
    private int age;
    private String tags;
}

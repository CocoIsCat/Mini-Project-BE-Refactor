package com.example.finance7.member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MemberSearchHistoryResponseDTO {

    private Long searchId;
    private String searchContent;
    private String createdAt;
}

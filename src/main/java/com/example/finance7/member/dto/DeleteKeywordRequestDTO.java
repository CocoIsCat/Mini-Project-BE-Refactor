package com.example.finance7.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DeleteKeywordRequestDTO {
    private Long searchId;
}

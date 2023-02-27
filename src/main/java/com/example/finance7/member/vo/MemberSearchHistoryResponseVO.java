package com.example.finance7.member.vo;

import com.example.finance7.member.dto.MemberSearchHistoryResponseDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MemberSearchHistoryResponseVO {

    private String status;
    private Integer dataNum;
    private List<MemberSearchHistoryResponseDTO> resultData;
}

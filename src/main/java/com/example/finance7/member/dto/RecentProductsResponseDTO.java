package com.example.finance7.member.dto;

import com.example.finance7.member.vo.RecentProductInfoVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class RecentProductsResponseDTO extends StatusResponseDTO {

    private int dataNum;
    private List<RecentProductInfoVO> resultData;
}

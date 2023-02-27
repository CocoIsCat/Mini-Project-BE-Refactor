package com.example.finance7.member.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RecentProductInfoVO {

    private String productName;
    private Long productId;
    private String category;
}

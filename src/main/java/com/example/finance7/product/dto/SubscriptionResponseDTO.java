package com.example.finance7.product.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class SubscriptionResponseDTO extends ProductResponseDTO {

    private String highRate;
    private String[] aboutRate;
    private Integer bound;
    private String[] purchase;
    private String[] qualification;
}

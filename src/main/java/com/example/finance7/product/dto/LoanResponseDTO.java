package com.example.finance7.product.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class LoanResponseDTO extends ProductResponseDTO{

    private String lowRate;
    private String highRate;
    private String[] bound;
    private String[] qualification;
}

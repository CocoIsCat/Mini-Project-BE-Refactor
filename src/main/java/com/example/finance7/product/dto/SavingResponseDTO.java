package com.example.finance7.product.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class SavingResponseDTO extends ProductResponseDTO{

    private String basicRate;
    private String primeRate;
    private String[] qualification;
    private String[] aboutPrimeRate;
}

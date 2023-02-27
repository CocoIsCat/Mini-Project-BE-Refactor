package com.example.finance7.cart.dto;

import com.example.finance7.product.entity.Savings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class SavingResponseDTO extends ProductResponseDTO {
    private String basicRate;
    private String primeRate;

    public SavingResponseDTO toDTO(Savings savings) {
        return SavingResponseDTO.builder()
                .productId(savings.getProductId())
                .category("savings")
                .productName(savings.getProductName())
                .companyName(savings.getCompanyName())
                .companyImage(savings.getCompanyImage())
                .basicRate(savings.getBasicRate())
                .primeRate(savings.getPrimeRate())
                .build();
    }
}

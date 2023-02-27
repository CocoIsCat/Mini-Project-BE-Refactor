package com.example.finance7.cart.dto;

import com.example.finance7.product.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class LoanResponseDTO extends ProductResponseDTO {
    private String lowRate;
    private String highRate;
    private String bound;

    public LoanResponseDTO toDTO(Loan loan) {
        return LoanResponseDTO.builder()
                .productId(loan.getProductId())
                .category("loan")
                .productName(loan.getProductName())
                .companyName(loan.getCompanyName())
                .companyImage(loan.getCompanyImage())
                .lowRate(loan.getLowRate())
                .highRate(loan.getHighRate())
                .bound(loan.getBound())
                .build();
    }
}

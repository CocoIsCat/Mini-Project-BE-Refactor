package com.example.finance7.cart.dto;

import com.example.finance7.product.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
public class CardResponseDTO extends ProductResponseDTO {
    private String[] benefits;
    private int annualFee;

    public CardResponseDTO toDTO(Card card) {
        return CardResponseDTO.builder()
                .productId(card.getProductId())
                .category("card")
                .productName(card.getProductName())
                .companyName(card.getCompanyName())
                .thumbnail(card.getThumbnail())
                .benefits(card.getBenefits().split("\\\\n"))
                .annualFee(card.getAnnualFee())
                .build();
    }
}

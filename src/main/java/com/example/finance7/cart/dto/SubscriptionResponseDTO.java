package com.example.finance7.cart.dto;

import com.example.finance7.product.entity.Savings;
import com.example.finance7.product.entity.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class SubscriptionResponseDTO extends ProductResponseDTO {
    private String highRate;

    public SubscriptionResponseDTO toDTO(Subscription subscription) {
        return SubscriptionResponseDTO.builder()
                .productId(subscription.getProductId())
                .category("subscription")
                .productName(subscription.getProductName())
                .companyName(subscription.getCompanyName())
                .companyImage(subscription.getCompanyImage())
                .highRate(subscription.getHighRate())
                .build();
    }
}

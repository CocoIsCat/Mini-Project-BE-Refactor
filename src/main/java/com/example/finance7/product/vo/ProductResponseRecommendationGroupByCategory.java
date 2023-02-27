package com.example.finance7.product.vo;

import com.example.finance7.product.dto.CardResponseDTO;
import com.example.finance7.product.dto.LoanResponseDTO;
import com.example.finance7.product.dto.SavingResponseDTO;
import com.example.finance7.product.dto.SubscriptionResponseDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponseRecommendationGroupByCategory {

    private String status;
    private List<CardResponseDTO> card;
    private List<LoanResponseDTO> loan;
    private List<SavingResponseDTO> savings;
    private List<SubscriptionResponseDTO> subscription;
}

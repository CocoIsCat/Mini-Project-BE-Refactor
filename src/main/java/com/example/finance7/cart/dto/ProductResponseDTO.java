package com.example.finance7.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class ProductResponseDTO {
        private long productId;
        private String category;
        private String productName;
        private String companyName;
        private String companyImage;
        private String thumbnail;
}

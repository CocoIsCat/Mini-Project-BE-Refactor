package com.example.finance7.cart.dto;

import lombok.*;

import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CartRequestDTO {

    @Positive
    private Long productId;
}

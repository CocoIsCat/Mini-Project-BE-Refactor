package com.example.finance7.cart.dto;

import com.example.finance7.cart.dto.ProductResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CartResponseDTO {
    private int dataNum;
    private List<ProductResponseDTO> resultData;
}

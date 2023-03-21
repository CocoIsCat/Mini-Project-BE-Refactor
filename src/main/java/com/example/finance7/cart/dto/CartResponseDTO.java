package com.example.finance7.cart.vo;

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
public class CartVO {
    private String status;
    private int dataNum;
    private List<ProductResponseDTO> resultData;
}

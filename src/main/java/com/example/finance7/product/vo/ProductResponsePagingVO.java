package com.example.finance7.product.vo;

import com.example.finance7.product.dto.ProductResponseDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class ProductResponsePagingVO {

    private String status;
    private Integer dataNum;
    private List<ProductResponseDTO> resultData = new ArrayList<>();
    private Integer currentPage;
    private Integer totalPage;
}

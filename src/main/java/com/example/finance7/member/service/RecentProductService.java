package com.example.finance7.member.service;

import com.example.finance7.member.dto.StatusResponseDTO;

public interface RecentProductService {

    StatusResponseDTO findRecentProductsInfo(String header);

    StatusResponseDTO addRecentProduct(Long productId, String header);
}
package com.example.finance7.member.controller;

import com.example.finance7.member.dto.RecentProductRequestDTO;
import com.example.finance7.member.dto.StatusResponseDTO;
import com.example.finance7.member.service.RecentProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class RecentProductController {

    private final RecentProductService recentProductService;

    /**
     * 최근 본 상품 조회
     *
     * @return status 정보, 최근 본 상품 개수, 최근 본 상품 정보 (response dto)
     */
    @GetMapping("/user/recentproducts")
    public StatusResponseDTO findRecentProductsInfo(HttpServletRequest request) {
        return recentProductService.findRecentProductsInfo(request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    /**
     * 최근 본 상품 추가
     *
     * @param recentProductRequestDTO 추가할 최근 본 상품 (request dto)
     * @return Status 정보 (response dto)
     */
    @PostMapping("/user/recentproducts")
    public StatusResponseDTO addRecentProduct(@RequestBody RecentProductRequestDTO recentProductRequestDTO, HttpServletRequest request) {
        return recentProductService.addRecentProduct(recentProductRequestDTO.getProductId(), request.getHeader(HttpHeaders.AUTHORIZATION));
    }
}

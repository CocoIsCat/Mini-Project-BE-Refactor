package com.example.finance7.member.service.impl;

import com.example.finance7.config.JwtProvider;
import com.example.finance7.member.dto.MemberRequestDTO;
import com.example.finance7.member.dto.RecentProductsResponseDTO;
import com.example.finance7.member.dto.StatusResponseDTO;
import com.example.finance7.member.entity.Member;
import com.example.finance7.member.entity.RecentProduct;
import com.example.finance7.member.repository.RecentProductRepository;
import com.example.finance7.member.service.MemberInfoService;
import com.example.finance7.member.service.MemberService;
import com.example.finance7.member.service.RecentProductService;
import com.example.finance7.member.vo.RecentProductInfoVO;
import com.example.finance7.product.entity.*;
import com.example.finance7.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Repository
public class RecentProductServiceImpl implements RecentProductService {

    private final RecentProductRepository recentProductRepository;
    private final MemberInfoService memberInfoService;
    private final ProductService productService;
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    /**
     * 최근 본 상품 response dto 생성
     *
     * @return status 정보, 최근 본 상품 개수, 최근 본 상품 정보 (response dto)
     */
    @Override
    public StatusResponseDTO findRecentProductsInfo(String header) {
        try {
            MemberRequestDTO memberRequestDTO = new MemberRequestDTO(jwtProvider.tokenToMember(header));
            Member member = memberService.findMemberByEmail(memberRequestDTO.getEmail());

            List<RecentProduct> recentProductList = recentProductRepository.findByMember(member);
            List<RecentProductInfoVO> resultData = new ArrayList<>();

            for (int i = 0; i < recentProductList.size(); i++) {
                Product product = recentProductList.get(i).getProduct();

                resultData.add(
                        RecentProductInfoVO.builder()
                                .productName(product.getProductName())
                                .productId(product.getProductId())
                                .category(checkType(product))
                                .build()
                );
            }

            return RecentProductsResponseDTO.builder()
                    .status("success")
                    .dataNum(recentProductList.size())
                    .resultData(resultData)
                    .build();
        } catch (NullPointerException e) {
            log.error(e.getMessage());

            return memberInfoService.makeStatusResponse("fail");
        }
    }

    /**
     * 카테고리 반환
     *
     * @param product 상품 유형 (Entity)
     * @return 카테고리 (String)
     */
    private String checkType(Product product) {
        if (product instanceof Card) {
            return "CARD";
        } else if (product instanceof Loan) {
            return "LOAN";
        } else if (product instanceof Savings) {
            return "SAVINGS";
        } else if (product instanceof Subscription) {
            return "SUBSCRIPTION";
        }
        return null;
    }

    /**
     * 최근 본 상품 추가
     *
     * @param productId 추가할 최근 본 상품 id (Long)
     * @return Status 정보 (response dto)
     */
    public StatusResponseDTO addRecentProduct(Long productId, String header) {
        try {
            MemberRequestDTO memberRequestDTO = new MemberRequestDTO(jwtProvider.tokenToMember(header));
            Member member = memberService.findMemberByEmail(memberRequestDTO.getEmail());

            Product product = productService.findProductByProductId(productId);
            RecentProduct recentProduct = recentProductRepository.findByMemberAndProduct(member, product);
            if (recentProduct != null) {
                recentProductRepository.delete(recentProduct);
            }

            List<RecentProduct> recentProductList = recentProductRepository.findByMember(member);
            if (recentProductList.size() >= 10) {
                recentProductRepository.delete(recentProductList.get(0));
            }

            recentProductRepository.save(
                    RecentProduct.builder()
                            .member(member)
                            .product(product)
                            .build()
            );

            return memberInfoService.makeStatusResponse("success");
        } catch (NullPointerException e) {
            log.error(e.getMessage());

            return memberInfoService.makeStatusResponse("fail");
        }
    }
}

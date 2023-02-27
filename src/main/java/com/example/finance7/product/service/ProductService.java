package com.example.finance7.product.service;

import com.example.finance7.product.entity.Product;
import com.example.finance7.product.vo.ProductResponsePagingVO;
import com.example.finance7.product.vo.ProductResponseRecommendationGroupByCategory;
import com.example.finance7.product.vo.ProductResponseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProductService {


    Product findProductByProductId(Long productId);

    /**
     * 상세 상품 조회
     * @param productId
     * @return
     */
    public ProductResponseVO selectProductDetail(Long productId);

    /**
     * 추천 상품 조회
     * @param tagString
     * @return
     */
    public ProductResponseRecommendationGroupByCategory recommendationProductsList(String tagString);

    /**
     * 카테고리별 태그 조회
     * @param category
     * @param page
     * @param tagString
     * @return
     */
    public ProductResponsePagingVO categoryList(String category, int page, String tagString);

    /**
     * 카테고리별 검색결과 조회
     * @param pageable
     * @param title
     * @return
     */
    public ProductResponsePagingVO categoryAndSearch(Pageable pageable,
                                                     String title,
                                                     String category);
}

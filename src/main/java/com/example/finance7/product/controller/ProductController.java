package com.example.finance7.product.controller;

import com.example.finance7.product.service.ProductService;
import com.example.finance7.product.vo.ProductResponsePagingVO;
import com.example.finance7.product.vo.ProductResponseRecommendationGroupByCategory;
import com.example.finance7.product.vo.ProductResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/items/{productId}")
    public ProductResponseVO productDetail(@PathVariable Long productId) {
        return productService.selectProductDetail(productId);
    }

    @GetMapping("/Recommendation/{tagString}")
    public ProductResponseRecommendationGroupByCategory recommendationProduct(@PathVariable String tagString) {
        return productService.recommendationProductsList(tagString);
    }

    @GetMapping("/items/all/{tagString}")
    public ProductResponsePagingVO groupByCategory(String category,int page, @PathVariable String tagString) {
        return productService.categoryList(category, page,tagString);
    }

    @GetMapping("/search")
    public ProductResponsePagingVO searchResultByCategory(String category,
                                                          String title,
                                                          @PageableDefault(size = 5) Pageable pageable) {

        return productService.categoryAndSearch(pageable, title, category);
    }
}

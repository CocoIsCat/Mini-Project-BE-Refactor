package com.example.finance7.product.service.impl;

import com.example.finance7.product.dto.*;
import com.example.finance7.product.entity.*;
import com.example.finance7.product.repository.*;
import com.example.finance7.product.service.ProductService;
import com.example.finance7.product.vo.ProductResponsePagingVO;
import com.example.finance7.product.vo.ProductResponseRecommendationGroupByCategory;
import com.example.finance7.product.vo.ProductResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final int SIZE = 5;
    private final ProductRepository productRepository;
    private final CardRepository cardRepository;
    private final LoanRepository loanRepository;
    private final SavingsRepository savingsRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Product findProductByProductId(Long productId) {

        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new NoSuchElementException("상품을 찾을 수 없습니다.");
        } else {
            return product.get();
        }
    }
    /**
     * productId를 이용하여 상세 상품 조회
     * @param productId
     * @return
     */
    @Override
    public ProductResponseVO selectProductDetail(Long productId) {

        try{
            Product product = productRepository.findById(productId).orElseThrow(
                    ()-> new NoSuchElementException("존재하지 않는 상품입니다.")
            );
            ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
            ProductResponseVO productResponseVO = getProductResponseVO();
            productResponseVO.getResultData().add(productResponseDTO);
            return productResponseVO;
        }catch (NoSuchElementException e){
            ProductResponseVO productResponseVO = new ProductResponseVO();
            productResponseVO.setStatus("failed 상품이 존재하지 않습니다.");
            return productResponseVO;
        }
    }

    private ProductResponseVO getProductResponseVO() {
        ProductResponseVO productResponseVO = new ProductResponseVO();
        productResponseVO.setDataNum(1);
        productResponseVO.setStatus("success");
        return productResponseVO;
    }

    /**
     * 추천 상품 조회
     * @param tagString
     * @return
     */
    @Override
    public ProductResponseRecommendationGroupByCategory recommendationProductsList(String tagString) {
        List<Product> products = productRepository.findAll();

        String[] tags = tagString.split("&");
        Map<Long, ProductResponseDTO> deduplication = deduplication(products, tags);
        List<ProductResponseDTO> result = new ArrayList<>(deduplication.values());
        List<CardResponseDTO> card = new ArrayList<>();
        List<LoanResponseDTO> loan = new ArrayList<>();
        List<SavingResponseDTO> savings = new ArrayList<>();
        List<SubscriptionResponseDTO> subscription = new ArrayList<>();

        for (ProductResponseDTO productResponseDTO : result) {
            if(productResponseDTO instanceof CardResponseDTO){
                card.add((CardResponseDTO) productResponseDTO);
            }else if(productResponseDTO instanceof LoanResponseDTO){
                loan.add((LoanResponseDTO) productResponseDTO);
            }else if (productResponseDTO instanceof SavingResponseDTO) {
                savings.add((SavingResponseDTO) productResponseDTO);
            }else {
                subscription.add((SubscriptionResponseDTO) productResponseDTO);
            }
        }
        if (result.size() == 0){
            return ProductResponseRecommendationGroupByCategory.builder()
                    .status("failed 검색결과가 없습니다.")
                    .build();
        } else {
            return ProductResponseRecommendationGroupByCategory.builder()
                    .status("success")
                    .card(card)
                    .loan(loan)
                    .savings(savings)
                    .subscription(subscription)
                    .build();
        }
    }

    /**
     * 맵을 이용하여 중복값 제거하는 메서드
     * @param products
     * @param tags
     * @return
     */
    private Map<Long, ProductResponseDTO> deduplication(List<Product> products, String[] tags) {
        Map<Long, ProductResponseDTO> duplicateResult = new HashMap<>();
        for (Product product : products) {
            for (String tag : tags) {
                if (product.getTags().contains(tag)) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    duplicateResult.put(product.getProductId(), productResponseDTO);
                }
            }
        }
        return duplicateResult;
    }

    /**
     * 카테고리별 상품 조회
     * @param page
     * @return
     */
    @Override
    public ProductResponsePagingVO categoryList(String category, int page, String tagString) {
        PageRequest pageRequest = PageRequest.of(page - 1, SIZE);
        String[] tags = tagString.split("&");
        if(tags[0].equals("noTag")){
            return withoutTags(pageRequest, category, page);
        }
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        int totalPage = 0;
        if (category == null || category.equals("")){
            if(tags.length == 1){
                Page<Product> allByTagsContaining = productRepository.findAllByTagsContaining(tags[0], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }else if(tags.length == 2){
                Page<Product> allByTagsContaining = productRepository.findAllByTagsContainingOrTagsContaining(tags[0], tags[1], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }else {
                Page<Product> allByTagsContaining = productRepository.findAllByTagsContainingOrTagsContainingOrTagsContaining(tags[0], tags[1], tags[2], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }
        }else if (category.equals("card")) {
            if(tags.length == 1){
                Page<Product> allByTagsContaining = cardRepository.findAllByTagsContaining(tags[0], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }else if(tags.length == 2){
                Page<Product> allByTagsContaining = cardRepository.findAllByTagsContainingOrTagsContaining(tags[0], tags[1], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }else {
                Page<Product> allByTagsContaining = cardRepository.findAllByTagsContainingOrTagsContainingOrTagsContaining(tags[0], tags[1], tags[2], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }
        }else if (category.equals("loan")) {
            if(tags.length == 1){
                Page<Product> allByTagsContaining = loanRepository.findAllByTagsContaining(tags[0], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }else if(tags.length == 2){
                Page<Product> allByTagsContaining = loanRepository.findAllByTagsContainingOrTagsContaining(tags[0], tags[1], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }else {
                Page<Product> allByTagsContaining = loanRepository.findAllByTagsContainingOrTagsContainingOrTagsContaining(tags[0], tags[1], tags[2], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }
        }else if (category.equals("savings")) {
            if(tags.length == 1){
                Page<Product> allByTagsContaining = savingsRepository.findAllByTagsContaining(tags[0], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }else if(tags.length == 2){
                Page<Product> allByTagsContaining = savingsRepository.findAllByTagsContainingOrTagsContaining(tags[0], tags[1], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }else {
                Page<Product> allByTagsContaining = savingsRepository.findAllByTagsContainingOrTagsContainingOrTagsContaining(tags[0], tags[1], tags[2], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }
        }else if (category.equals("subscription")) {
            if(tags.length == 1){
                Page<Product> allByTagsContaining = subscriptionRepository.findAllByTagsContaining(tags[0], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }else if(tags.length == 2){
                Page<Product> allByTagsContaining = subscriptionRepository.findAllByTagsContainingOrTagsContaining(tags[0], tags[1], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }else {
                Page<Product> allByTagsContaining = subscriptionRepository.findAllByTagsContainingOrTagsContainingOrTagsContaining(tags[0], tags[1], tags[2], pageRequest);
                totalPage = allByTagsContaining.getTotalPages();
                for (Product product : allByTagsContaining) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    productResponseDTOList.add(productResponseDTO);
                }
            }

        }
        return getProductResponsePagingVO(page, productResponseDTOList, totalPage);
    }

    /**
     * Tag 가 없을시 이쪽 로직을 수행한다.
     */
    public ProductResponsePagingVO withoutTags(PageRequest pageRequest, String category, int page) {
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        int totalPage = 0;
        if(category == null || category.equals("")){
            Page<Product> products = productRepository.findAll(pageRequest);
            totalPage = products.getTotalPages();
            for (Product product : products) {
                ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                productResponseDTOList.add(productResponseDTO);
            }
        } else if (category.equals("card")) {
            Page<Card> cards = cardRepository.findAll(pageRequest);
            totalPage = cards.getTotalPages();
            for (Card card : cards) {
                ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(card);
                productResponseDTOList.add(productResponseDTO);
            }
        } else if (category.equals("loan")) {
            Page<Loan> loans = loanRepository.findAll(pageRequest);
            totalPage = loans.getTotalPages();
            for (Loan loan : loans) {
                ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(loan);
                productResponseDTOList.add(productResponseDTO);
            }
        } else if (category.equals("savings")) {
            Page<Savings> savings = savingsRepository.findAll(pageRequest);
            totalPage = savings.getTotalPages();
            for (Savings saving : savings) {
                ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(saving);
                productResponseDTOList.add(productResponseDTO);
            }
        } else if (category.equals("subscription")) {
            Page<Subscription> subscriptions = subscriptionRepository.findAll(pageRequest);
            totalPage = subscriptions.getTotalPages();
            for (Subscription subscription : subscriptions) {
                ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(subscription);
                productResponseDTOList.add(productResponseDTO);
            }
        }
        return getProductResponsePagingVO(page, productResponseDTOList, totalPage);
    }


    /**
     * 데이터 결과가 있는지 없는지 반환 데이터 판별 메서드
     * @param page
     * @param productResponseDTOList
     * @param totalPage
     * @return
     */
    private ProductResponsePagingVO getProductResponsePagingVO(int page, List<ProductResponseDTO> productResponseDTOList, int totalPage) {
        if(productResponseDTOList.size() == 0){
            return ProductResponsePagingVO.builder()
                    .dataNum(0)
                    .status("failed 검색결과가 없습니다.")
                    .build();

        }else {
            return ProductResponsePagingVO.builder()
                    .dataNum(productResponseDTOList.size())
                    .status("success")
                    .currentPage(page)
                    .totalPage(totalPage)
                    .resultData(productResponseDTOList)
                    .build();

        }
    }

    /**
     * 카테고리별 검색결과 조회
     * @param pageable
     * @param title
     * @param category
     * @return
     */
    @Override
    public ProductResponsePagingVO categoryAndSearch(Pageable pageable, String title, String category) {

        List<ProductResponseDTO> pagingList = new ArrayList<>();
        int totalPage = 0;
        if (category == null || category.equals("")){
            Page<Product> productList = productRepository.findAllByProductNameContaining(title,pageable);
            totalPage = productList.getTotalPages();
            pagingListAddProductResponseDTO(pagingList, productList);
        }else if (category.equals("card")) {
            Page<Product> cardList = cardRepository.findAllByProductNameContaining(title, pageable);
            totalPage = cardList.getTotalPages();
            pagingListAddProductResponseDTO(pagingList, cardList);
        }else if (category.equals("loan")) {
            Page<Product> loanList = loanRepository.findAllByProductNameContaining(title, pageable);
            totalPage = loanList.getTotalPages();
            pagingListAddProductResponseDTO(pagingList, loanList);
        }else if (category.equals("savings")) {
            Page<Product> savingsList = savingsRepository.findAllByProductNameContaining(title, pageable);
            totalPage = savingsList.getTotalPages();
            pagingListAddProductResponseDTO(pagingList, savingsList);
        }else if (category.equals("subscription")) {
            Page<Product> subscriptionList = subscriptionRepository.findAllByProductNameContaining(title, pageable);
            totalPage = subscriptionList.getTotalPages();
            pagingListAddProductResponseDTO(pagingList, subscriptionList);
        }

        return getProductResponsePagingVO(pageable, pagingList, totalPage);
    }

    /**
     * 검색결과가 있냐 없냐를 따져서 success failed 를 판별해주는 메서드 추출
     * @param pageable
     * @param pagingList
     * @param totalPage
     * @return
     */
    private ProductResponsePagingVO getProductResponsePagingVO(Pageable pageable,
                                                               List<ProductResponseDTO> pagingList,
                                                               int totalPage) {
        if(pagingList.size() == 0){
            return ProductResponsePagingVO.builder()
                    .dataNum(pagingList.size())
                    .status("failed 검색결과가 없습니다.")
                    .build();

        }else {
            return ProductResponsePagingVO.builder()
                    .dataNum(pagingList.size())
                    .status("success")
                    .resultData(pagingList)
                    .currentPage(pageable.getPageNumber() + 1)
                    .totalPage(totalPage)
                    .build();
        }
    }

    /**
     * 중복되는 로직 메서드 추출
     * @param pagingList
     * @param productList
     */
    private void pagingListAddProductResponseDTO(List<ProductResponseDTO> pagingList, Page<Product> productList) {
        for (Product product : productList) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
            pagingList.add(productResponseDTO);
        }
    }

}

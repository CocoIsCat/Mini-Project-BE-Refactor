package com.example.finance7.product.repository;

import com.example.finance7.product.entity.Card;
import com.example.finance7.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Page<Product> findAllByProductNameContaining(String productName, Pageable pageable);

    Page<Product> findAllByTagsContaining(String tags,
                                          PageRequest pageRequest);
    Page<Product> findAllByTagsContainingOrTagsContaining(String tags1,
                                                          String tags2,
                                                          PageRequest pageRequest);
    Page<Product> findAllByTagsContainingOrTagsContainingOrTagsContaining(String tags1,
                                                                          String tags2,
                                                                          String tags3,
                                                                          PageRequest pageRequest);
}

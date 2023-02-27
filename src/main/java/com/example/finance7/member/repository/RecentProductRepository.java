package com.example.finance7.member.repository;

import com.example.finance7.member.entity.Member;
import com.example.finance7.member.entity.RecentProduct;
import com.example.finance7.product.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecentProductRepository extends JpaRepository<RecentProduct, Long> {

    @EntityGraph(attributePaths = {"product"})
    List<RecentProduct> findByMember(Member member);

    RecentProduct findByMemberAndProduct(Member member, Product product);
}

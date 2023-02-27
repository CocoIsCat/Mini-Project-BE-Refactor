package com.example.finance7.cart.repository;

import com.example.finance7.cart.entity.Cart;
import com.example.finance7.member.entity.Member;
import com.example.finance7.product.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Boolean existsByMemberAndProduct(Member member, Product product);

    @EntityGraph(attributePaths = {"product"})
    List<Cart> findCartsByMember(Member member);

    Long deleteCartByMemberAndProduct(Member member, Product product);

    @Modifying
    @Query("delete from Cart c where c.member = :member")
    int deleteByMember(@Param("member") Member member);
}

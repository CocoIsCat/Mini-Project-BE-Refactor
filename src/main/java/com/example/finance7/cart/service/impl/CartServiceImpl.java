package com.example.finance7.cart.service.impl;

import com.example.finance7.cart.dto.*;
import com.example.finance7.cart.entity.Cart;
import com.example.finance7.cart.repository.CartRepository;
import com.example.finance7.cart.service.CartService;
import com.example.finance7.cart.dto.CartResponseDTO;
import com.example.finance7.cart.vo.SimpleVO;
import com.example.finance7.member.dto.MemberRequestDTO;
import com.example.finance7.member.entity.Member;
import com.example.finance7.member.service.MemberService;
import com.example.finance7.product.entity.*;
import com.example.finance7.product.service.ProductService;
import com.example.finance7.config.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final MemberService memberService;
    private final ProductService productService;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;

    /**
     * 장바구니 상품 추가
     * @param productId
     * @return
     */
    @Override
    public void addCart(Long productId, String header) {
        MemberRequestDTO memberRequestDTO = new MemberRequestDTO(jwtProvider.tokenToMember(header));
        Member member = memberService.findMemberByEmail(memberRequestDTO.getEmail()).get();
        Product product = productService.findProductByProductId(productId).get();
        if (!cartRepository.existsByMemberAndProduct(member, product)) {
            Cart item = Cart.builder()
                    .member(member)
                    .product(product)
                    .build();
            cartRepository.save(item);
        }
    }

    /**
     * 회원이 가진 장바구니 상품 모두 보기
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public CartResponseDTO selectAllCartProducts(String header) {
        MemberRequestDTO memberRequestDTO = new MemberRequestDTO(jwtProvider.tokenToMember(header));
        Member member = memberService.findMemberByEmail(memberRequestDTO.getEmail()).get();
        List<Cart> items = cartRepository.findCartsByMember(member);
        return CartResponseDTO.builder()
                .dataNum(items.size())
                .resultData(items.stream()
                        .map(i -> i.toResponseDTO())
                        .collect(Collectors.toList()))
                .build();
    }

    /**
     * 장바구니 상품 삭제
     *
     * @param productId
     * @param header
     * @return
     */
    @Override
    @Transactional
    public SimpleVO deleteItem(Long productId, String header) {
        try {
            MemberRequestDTO memberRequestDTO = new MemberRequestDTO(jwtProvider.tokenToMember(header));
            Member member = memberService.findMemberByEmail(memberRequestDTO.getEmail()).get();
            Product product = productService.findProductByProductId(productId).get();
            if(cartRepository.existsByMemberAndProduct(member, product)) {
                cartRepository.deleteCartByMemberAndProduct(member, product);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            return SimpleVO.builder()
                    .message("failed:장바구니 삭제에 실패했습니다.")
                    .build();
        }
        return SimpleVO.builder()
                .message("success")
                .build();
    }

    @Override
    @Transactional
    public DeleteResponseDTO deleteAllItems(String header) {
        MemberRequestDTO memberRequestDTO = new MemberRequestDTO(jwtProvider.tokenToMember(header));
        Member member = memberService.findMemberByEmail(memberRequestDTO.getEmail()).get();
        int num = cartRepository.deleteByMember(member);
        return DeleteResponseDTO.builder()
                .status("success")
                .deletedNum(num)
                .build();
    }
}

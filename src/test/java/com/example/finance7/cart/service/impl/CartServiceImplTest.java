package com.example.finance7.cart.service.impl;

import com.example.finance7.cart.repository.CartRepository;
import com.example.finance7.cart.service.CartService;
import com.example.finance7.cart.vo.SimpleVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceImplTest {

    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepository;

    @Test

    void 장바구니_상품_추가() {
        //given
        Long productId1 = 2L;
        Long productId2 = 1L;
        Long productId3 = 3L;
        Long productId4 = 4L;

        //when
/*        cartService.addCart(productId1);
        cartService.addCart(productId2);
        cartService.addCart(productId3);
        cartService.addCart(productId4);*/

        //then
        Assertions.assertThat(cartRepository.count()).isEqualTo(4);
    }

    @Test
    @Transactional
    void 장바구니_상품_중복체크() {
        //given
        Long memberId1 = 1L;
        Long productId1 = 2L;

        Long memberId2 = 1L;
        Long productId2 = 1L;


/*        cartService.addCart(productId1);
        SimpleVO simpleVO = cartService.addCart(productId1);*/

/*        Assertions.assertThat(simpleVO.getStatus().contains("success")).isTrue();*/
    }
}
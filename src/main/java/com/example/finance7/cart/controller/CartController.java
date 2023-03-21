package com.example.finance7.cart.controller;

import com.example.finance7.cart.dto.CartRequestDTO;
import com.example.finance7.cart.dto.DeleteResponseDTO;
import com.example.finance7.cart.service.CartService;
import com.example.finance7.cart.dto.CartResponseDTO;
import com.example.finance7.cart.vo.SimpleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * 장바구니에 상품 추가
     * @param requestDTO
     * @param request
     * @return
     */
    @PostMapping("/cart")
    public ResponseEntity addCart(@Valid @RequestBody CartRequestDTO requestDTO, HttpServletRequest request) {
        cartService.addCart(requestDTO.getProductId(), request.getHeader(HttpHeaders.AUTHORIZATION));
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 장바구니 모든 상품 조회
     * @param request
     * @return
     */
    @GetMapping("/cart")
    public ResponseEntity<CartResponseDTO> selectAllCartProducts(HttpServletRequest request) {
        return new ResponseEntity(cartService.selectAllCartProducts(request.getHeader(HttpHeaders.AUTHORIZATION)), HttpStatus.OK);
    }

    /**
     * 장바구니에서 상품 삭제
     * @param requestDTO
     * @param request
     * @return
     */
    @DeleteMapping("/cart")
    public ResponseEntity deleteItem(@Valid @RequestBody CartRequestDTO requestDTO, HttpServletRequest request) {
        cartService.deleteItem(requestDTO.getProductId(), request.getHeader(HttpHeaders.AUTHORIZATION));
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 장바구니 상품 모두 삭제
     * @param request
     * @return
     */
    @DeleteMapping("/cart/all")
    public ResponseEntity<DeleteResponseDTO> deleteAllItems(HttpServletRequest request) {
        return new ResponseEntity(cartService.deleteAllItems(request.getHeader(HttpHeaders.AUTHORIZATION)), HttpStatus.OK);
    }
}

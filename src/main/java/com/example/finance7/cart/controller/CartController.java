package com.example.finance7.cart.controller;

import com.example.finance7.cart.dto.CartRequestDTO;
import com.example.finance7.cart.dto.DeleteResponseDTO;
import com.example.finance7.cart.service.CartService;
import com.example.finance7.cart.vo.CartVO;
import com.example.finance7.cart.vo.SimpleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public SimpleVO addCart(@RequestBody CartRequestDTO requestDTO, HttpServletRequest request) {
        return cartService.addCart(requestDTO.getProductId(), request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    @GetMapping("/cart")
    public CartVO selectAllCartProducts(HttpServletRequest request) {
        return cartService.selectAllCartProducts(request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    @DeleteMapping("/cart")
    public SimpleVO deleteItem(@RequestBody CartRequestDTO requestDTO, HttpServletRequest request) {
        return cartService.deleteItem(requestDTO.getProductId(), request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    @DeleteMapping("/cart/all")
    public DeleteResponseDTO deleteAllItems(HttpServletRequest request) {
        return cartService.deleteAllItems(request.getHeader(HttpHeaders.AUTHORIZATION));
    }
}

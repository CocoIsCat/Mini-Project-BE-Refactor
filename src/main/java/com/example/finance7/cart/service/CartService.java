package com.example.finance7.cart.service;

import com.example.finance7.cart.dto.DeleteResponseDTO;
import com.example.finance7.cart.vo.CartVO;
import com.example.finance7.cart.vo.SimpleVO;
import org.springframework.stereotype.Service;

@Service
public interface CartService {


    CartVO selectAllCartProducts(String header);

    SimpleVO deleteItem(Long productId, String header);

    DeleteResponseDTO deleteAllItems(String header);

    SimpleVO addCart(Long productId, String header);
}

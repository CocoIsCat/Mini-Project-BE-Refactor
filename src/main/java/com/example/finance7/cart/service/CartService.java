package com.example.finance7.cart.service;

import com.example.finance7.cart.dto.DeleteResponseDTO;
import com.example.finance7.cart.dto.CartResponseDTO;
import com.example.finance7.cart.vo.SimpleVO;
import org.springframework.stereotype.Service;

@Service
public interface CartService {


    CartResponseDTO selectAllCartProducts(String header);

    void deleteItem(Long productId, String header);

    DeleteResponseDTO deleteAllItems(String header);

    void addCart(Long productId, String header);
}

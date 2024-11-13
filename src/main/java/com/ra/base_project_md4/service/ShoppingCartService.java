package com.ra.base_project_md4.service;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.dto.request.ProductRequest;
import com.ra.base_project_md4.model.dto.request.ShoppingCartRequest;
import com.ra.base_project_md4.model.dto.response.ProductResponse;
import com.ra.base_project_md4.model.dto.response.ShoppingCartResponse;
import com.ra.base_project_md4.model.entity.ShoppingCart;
import com.ra.base_project_md4.model.entity.User;

import java.util.List;

public interface ShoppingCartService {
   List<ShoppingCart> findAllCartByUser(Long userId);

    ShoppingCartResponse save(ShoppingCartRequest shoppingCartRequest, Long userId);

        ShoppingCartResponse updateQuantity(ShoppingCartRequest shoppingCartRequest, Long userId,Long productDetailId) throws CustomException;

    void delete(Long id);

    void deleteAll(Long userId);
}

package com.ra.base_project_md4.service;

import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.model.entity.WishList;

import java.util.List;

public interface WishListService {
    String addWishList(Long userId, Long productId);
    List<Product> getWishList(Long userId);
    List<WishList> findAll();
    void removeWishList(Long userId, Long productId);
}

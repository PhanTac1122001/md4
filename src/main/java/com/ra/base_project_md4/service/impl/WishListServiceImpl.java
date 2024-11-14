package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.model.entity.User;
import com.ra.base_project_md4.model.entity.WishList;
import com.ra.base_project_md4.repository.WishListRepository;
import com.ra.base_project_md4.service.ProductService;
import com.ra.base_project_md4.service.UserService;
import com.ra.base_project_md4.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;
    private final UserService userService;
    private final ProductService productService;
    @Override
    public String  addWishList(Long userId, Long productId) {
        User user = userService.findById(userId);
        Product product = productService.findById(productId);
        Optional<WishList> existingWishList = wishListRepository.findByUserAndProduct(user, product);
        if (existingWishList.isPresent()) {

            wishListRepository.delete(existingWishList.get());
            return "deleted";
        } else {

            WishList wishList = new WishList();
            wishList.setUser(user);
            wishList.setProduct(product);
            wishListRepository.save(wishList);
            return "added";
        }

    }

    @Override
    public List<Product> getWishList(Long userId) {
        User user = userService.findById(userId);
        List<WishList> wishLists = wishListRepository.findWishListByUser(user);
        return wishLists.stream()
                .map(WishList::getProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<WishList> findAll() {
        return wishListRepository.findAll();
    }


    @Override
    public void removeWishList(Long userId, Long productId) {
        User user = userService.findById(userId);
        Product product = productService.findById(productId);
        WishList wishList = wishListRepository.findByUserAndProduct(user,product)
                .orElseThrow(() -> new RuntimeException("Product not found in wishlist"));
        wishListRepository.delete(wishList);
    }
}

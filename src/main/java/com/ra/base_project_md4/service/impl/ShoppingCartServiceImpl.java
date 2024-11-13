package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.dto.request.CategoryRequest;
import com.ra.base_project_md4.model.dto.request.ShoppingCartRequest;
import com.ra.base_project_md4.model.dto.response.CategoryResponse;
import com.ra.base_project_md4.model.dto.response.ShoppingCartResponse;
import com.ra.base_project_md4.model.entity.Category;
import com.ra.base_project_md4.model.entity.ProductDetail;
import com.ra.base_project_md4.model.entity.ShoppingCart;
import com.ra.base_project_md4.model.entity.User;
import com.ra.base_project_md4.repository.CategoryRepository;
import com.ra.base_project_md4.repository.ProductDetailRepository;
import com.ra.base_project_md4.repository.ShoppingCartRepository;
import com.ra.base_project_md4.service.CategoryService;
import com.ra.base_project_md4.service.ShoppingCartService;
import com.ra.base_project_md4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final ProductDetailRepository productDetailRepository;

    @Override
    public List<ShoppingCart> findAllCartByUser(Long userId) {
        User user = userService.findById(userId);

        return shoppingCartRepository.findShoppingCartByUser(user);
    }

    @Override
    public ShoppingCartResponse save(ShoppingCartRequest shoppingCartRequest, Long userId) {
        ProductDetail productDetail = productDetailRepository.findById(shoppingCartRequest.getProductDetailId()).orElseThrow();
        User user = userService.findById(userId);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserAndProductDetail(user, productDetail);
        if (shoppingCart != null) {

            int newQuantity = shoppingCart.getQuantity() + shoppingCartRequest.getQuantity();


            if (newQuantity >= 1) {
                shoppingCart.setQuantity(newQuantity);
            } else {
                throw new IllegalArgumentException("Số lượng sản phẩm phải lớn hơn hoặc bằng 1.");
            }
        } else {

            if (shoppingCartRequest.getQuantity() < 1) {
                throw new IllegalArgumentException("Số lượng sản phẩm phải lớn hơn hoặc bằng 1.");
            }
            shoppingCart = ShoppingCart.builder()
                    .productDetail(productDetail)
                    .user(user)
                    .quantity(shoppingCartRequest.getQuantity())
                    .build();
        }

        ShoppingCart shoppingCartNew = shoppingCartRepository.save(shoppingCart);
        return ShoppingCartResponse.builder()
                .id(shoppingCartNew.getId())
                .productDetail(shoppingCartNew.getProductDetail())
                .user(shoppingCartNew.getUser())
                .quantity(shoppingCartNew.getQuantity())
                .build();
    }

    @Override
    public ShoppingCartResponse updateQuantity(ShoppingCartRequest shoppingCartRequest, Long userId, Long productDetailId) throws CustomException {
        ProductDetail productDetail = productDetailRepository.findById(productDetailId).orElseThrow();
        User user = userService.findById(userId);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserAndProductDetail(user, productDetail);

        if (shoppingCart != null) {

            int newQuantity = shoppingCartRequest.getQuantity();


            if (newQuantity >= 1) {
                shoppingCart.setQuantity(newQuantity);
            } else {
                throw new CustomException("Số lượng sản phẩm phải lớn hơn hoặc bằng 1.");
            }
        } else {

            if (shoppingCartRequest.getQuantity() < 1) {
                throw new CustomException("Số lượng sản phẩm phải lớn hơn hoặc bằng 1.");
            }
            shoppingCart = ShoppingCart.builder()
                    .productDetail(productDetail)
                    .user(user)
                    .quantity(shoppingCartRequest.getQuantity())
                    .build();
        }


        ShoppingCart shoppingCartNew = shoppingCartRepository.save(shoppingCart);


        return ShoppingCartResponse.builder()
                .id(shoppingCartNew.getId())
                .productDetail(shoppingCartNew.getProductDetail())
                .user(shoppingCartNew.getUser())
                .quantity(shoppingCartNew.getQuantity())
                .build();
    }

    @Override
    public void delete(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public void deleteAll(Long userId) {
        User user = userService.findById(userId);
        shoppingCartRepository.deleteAllByUser(user);
    }
}

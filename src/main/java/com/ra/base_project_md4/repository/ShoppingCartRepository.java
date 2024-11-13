package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.ProductDetail;
import com.ra.base_project_md4.model.entity.ShoppingCart;
import com.ra.base_project_md4.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    List<ShoppingCart> findShoppingCartByUser(User user);
    ShoppingCart findByUserAndProductDetail(User user, ProductDetail productDetail);
    @Transactional
    void deleteAllByUser(User user);
}

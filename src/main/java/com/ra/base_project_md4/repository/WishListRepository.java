package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.model.entity.ShoppingCart;
import com.ra.base_project_md4.model.entity.User;
import com.ra.base_project_md4.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList,Long> {
    List<WishList> findWishListByUser(User user);
    Optional<WishList> findByUserAndProduct(User user, Product product);
}

package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.Order;
import com.ra.base_project_md4.model.entity.ShoppingCart;
import com.ra.base_project_md4.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrderByUser(User user);
}

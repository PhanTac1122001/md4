package com.ra.base_project_md4.service;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.dto.request.OrderRequest;
import com.ra.base_project_md4.model.dto.response.OrderResponse;
import com.ra.base_project_md4.model.entity.Order;
import com.ra.base_project_md4.model.entity.User;

import java.util.List;

public interface OrderService {
    List<Order> findOrderByUser(Long userId);
    OrderResponse createOrder(Long userId, OrderRequest orderRequest) throws CustomException;
}

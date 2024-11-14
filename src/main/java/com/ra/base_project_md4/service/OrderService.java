package com.ra.base_project_md4.service;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.constant.OrderStatus;
import com.ra.base_project_md4.model.dto.request.OrderRequest;
import com.ra.base_project_md4.model.dto.response.OrderResponse;
import com.ra.base_project_md4.model.entity.Order;
import com.ra.base_project_md4.model.entity.User;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    OrderResponse createOrder(Long userId, OrderRequest orderRequest) throws CustomException;
    List<OrderResponse> getOrderHistory(Long userId);

    List<Order> findOrderBySerialNumberAndUser(String serialNumber,Long userId);

    List<Order> findOrderByStatusAndUser(OrderStatus status, Long userId);

    List<Order> findOrderByStatus(OrderStatus status);

    Order findById(Long id);

    OrderResponse updateOrder(Long id,OrderStatus status) throws CustomException;
}

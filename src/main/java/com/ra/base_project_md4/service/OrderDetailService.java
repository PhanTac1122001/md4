package com.ra.base_project_md4.service;

import com.ra.base_project_md4.model.constant.OrderStatus;
import com.ra.base_project_md4.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findByOrderStatus(OrderStatus status);
}

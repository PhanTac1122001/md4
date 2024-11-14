package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.model.constant.OrderStatus;
import com.ra.base_project_md4.model.entity.OrderDetail;
import com.ra.base_project_md4.repository.OrderDetailRepository;
import com.ra.base_project_md4.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetail> findByOrderStatus(OrderStatus status) {
        return orderDetailRepository.findByOrderStatus(status);
    }
}

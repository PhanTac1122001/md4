package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.constant.OrderStatus;
import com.ra.base_project_md4.model.dto.request.OrderRequest;
import com.ra.base_project_md4.model.dto.response.OrderResponse;
import com.ra.base_project_md4.model.entity.*;
import com.ra.base_project_md4.repository.OrderDetailRepository;
import com.ra.base_project_md4.repository.OrderRepository;
import com.ra.base_project_md4.service.OrderService;
import com.ra.base_project_md4.service.ShoppingCartService;
import com.ra.base_project_md4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ShoppingCartService shoppingCartService;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<Order> findOrderByUser(Long userId) {
        User user = userService.findById(userId);

        return orderRepository.findOrderByUser(user);
    }

    @Override
    public OrderResponse createOrder(Long userId, OrderRequest orderRequest) throws CustomException {
        User user = userService.findById(userId);

        List<ShoppingCart> shoppingCarts = shoppingCartService.findAllCartByUser(userId);
        String sku = UUID.randomUUID().toString();
        if (shoppingCarts.isEmpty()) {
            throw new CustomException("Giỏ hàng của bạn đang trống");
        }

        double totalPrice = shoppingCarts.stream()
                .mapToDouble(cart -> cart.getProductDetail().getPrice() * cart.getQuantity())
                .sum();
        Date createdAt = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdAt);
        calendar.add(Calendar.DAY_OF_YEAR, 4);
        Date receivedAt = calendar.getTime();
        Order order = Order.builder()
                .serialNumber(sku)
                .user(user)
                .totalPrice(totalPrice)
                .status(OrderStatus.WAITING)
                .note(orderRequest.getNote())
                .receiveName(orderRequest.getReceiveName())
                .receiveAddress(orderRequest.getReceiveAddress())
                .receivePhone(orderRequest.getReceivePhone())
                .createdAt(createdAt)
                .receivedAt(receivedAt)
                .build();

        Order savedOrder = orderRepository.save(order);


        shoppingCarts.forEach(cart -> {
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(savedOrder)
                    .productDetail(cart.getProductDetail())
                    .name(cart.getProductDetail().getProduct().getProductName())
                    .unitPrice(cart.getProductDetail().getPrice())
                    .quantity(cart.getQuantity())
                    .build();

            orderDetailRepository.save(orderDetail);
        });

        shoppingCartService.deleteAll(userId);


        return OrderResponse.builder()
                .id(savedOrder.getId())
                .user(savedOrder.getUser())
                .note(savedOrder.getNote())
                .serialNumber(savedOrder.getSerialNumber())
                .totalPrice(savedOrder.getTotalPrice())
                .status(savedOrder.getStatus())
                .createdAt(savedOrder.getCreatedAt())
                .receiveName(savedOrder.getReceiveName())
                .receiveAddress(savedOrder.getReceiveAddress())
                .receivePhone(savedOrder.getReceivePhone())
                .createdAt(savedOrder.getCreatedAt())
                .receivedAt(savedOrder.getReceivedAt())
                .build();
    }
}

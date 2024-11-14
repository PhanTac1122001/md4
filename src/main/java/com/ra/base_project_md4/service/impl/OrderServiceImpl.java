package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.constant.OrderStatus;
import com.ra.base_project_md4.model.dto.request.OrderRequest;
import com.ra.base_project_md4.model.dto.response.OrderResponse;
import com.ra.base_project_md4.model.entity.*;
import com.ra.base_project_md4.repository.OrderDetailRepository;
import com.ra.base_project_md4.repository.OrderRepository;
import com.ra.base_project_md4.service.OrderService;
import com.ra.base_project_md4.service.ProductDetailService;
import com.ra.base_project_md4.service.ShoppingCartService;
import com.ra.base_project_md4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ShoppingCartService shoppingCartService;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductDetailService productDetailService;
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
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
            ProductDetail productDetail = cart.getProductDetail();
            int currentStock = productDetail.getStock();
            int quantityOrdered = cart.getQuantity();

            if (quantityOrdered > currentStock) {
                try {
                    throw new CustomException("Không đủ hàng trong kho cho sản phẩm: " + productDetail.getProduct().getProductName());
                } catch (CustomException e) {
                    throw new RuntimeException(e);
                }
            }
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(savedOrder)
                    .productDetail(cart.getProductDetail())
                    .name(cart.getProductDetail().getProduct().getProductName())
                    .unitPrice(cart.getProductDetail().getPrice())
                    .quantity(cart.getQuantity())
                    .build();

            orderDetailRepository.save(orderDetail);
            productDetail.setStock(currentStock - quantityOrdered);
            productDetailService.save(productDetail);
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

    @Override
    public List<OrderResponse> getOrderHistory(Long userId) {
        User user=userService.findById(userId);
        List<Order> orders = orderRepository.findOrderByUser(user);

        // Chuyển các đơn hàng thành danh sách các OrderResponse
        return orders.stream()
                .map(order -> OrderResponse.builder()
                        .id(order.getId())
                        .serialNumber(order.getSerialNumber())
                        .totalPrice(order.getTotalPrice())
                        .status(order.getStatus())
                        .createdAt(order.getCreatedAt())
                        .receivedAt(order.getReceivedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderBySerialNumberAndUser(String serialNumber,Long userId) {
        User user=userService.findById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        return orderRepository.findOrderBySerialNumberAndUser(serialNumber,user);
    }

    @Override
    public List<Order> findOrderByStatusAndUser(OrderStatus status, Long userId) {
        User user=userService.findById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        return orderRepository.findOrderByStatusAndUser(status,user);
    }

    @Override
    public List<Order> findOrderByStatus(OrderStatus status) {
        return orderRepository.findOrderByStatus(status);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderStatus status) throws CustomException {
        Order order = orderRepository.findById(id)

                .orElseThrow(() -> new CustomException("Không tìm thấy đơn hàng với ID: " + id));



        if (!isStatusTransitionAllowed(order.getStatus(), status)) {
            throw new CustomException("Chuyển đổi trạng thái không hợp lệ từ " + order.getStatus() + " sang " + status);
        }
        order.setStatus(status);
        Order orderUpdate=orderRepository.save(order);
        return OrderResponse.builder()
                .id(orderUpdate.getId())
                .serialNumber(orderUpdate.getSerialNumber())
                .user(orderUpdate.getUser())
                .note(orderUpdate.getNote())
                .receiveAddress(orderUpdate.getReceiveAddress())
                .receiveName(orderUpdate.getReceiveName())
                .receivePhone(orderUpdate.getReceivePhone())
                .createdAt(orderUpdate.getCreatedAt())
                .receivedAt(orderUpdate.getReceivedAt())
                .status(orderUpdate.getStatus())
                .build();
    }
    private boolean isStatusTransitionAllowed(OrderStatus currentStatus, OrderStatus newStatus) {
        switch (currentStatus) {
            case WAITING:
                return newStatus == OrderStatus.CONFIRM || newStatus == OrderStatus.CANCEL || newStatus == OrderStatus.DENIED;
            case CONFIRM:
                return newStatus == OrderStatus.DELIVERY || newStatus == OrderStatus.CANCEL;
            case DELIVERY:
                return newStatus == OrderStatus.SUCCESS || newStatus == OrderStatus.CANCEL;
            case SUCCESS:

                return false ;
            case CANCEL:
                return false ;
            case DENIED:
                return false ;
            default:
                return false;
        }
        }

}

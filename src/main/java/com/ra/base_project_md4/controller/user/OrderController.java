package com.ra.base_project_md4.controller.user;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.dto.request.OrderRequest;
import com.ra.base_project_md4.model.dto.response.OrderResponse;
import com.ra.base_project_md4.model.entity.Order;
import com.ra.base_project_md4.model.entity.ShoppingCart;
import com.ra.base_project_md4.security.UserPrinciple;
import com.ra.base_project_md4.service.OrderService;
import com.ra.base_project_md4.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(@AuthenticationPrincipal UserPrinciple userPrinciple) {
        Long userId=userPrinciple.getUser().getId();
        return new ResponseEntity<>(orderService.findOrderByUser(userId), HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<OrderResponse> checkout(@AuthenticationPrincipal UserPrinciple userPrinciple, @RequestBody OrderRequest orderRequest) throws CustomException {
        Long userId=userPrinciple.getUser().getId();
        return new ResponseEntity<>(orderService.createOrder(userId,orderRequest), HttpStatus.OK);

    }
}

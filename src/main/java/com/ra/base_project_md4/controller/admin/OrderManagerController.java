package com.ra.base_project_md4.controller.admin;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.constant.OrderStatus;
import com.ra.base_project_md4.model.dto.response.OrderResponse;
import com.ra.base_project_md4.model.entity.Order;
import com.ra.base_project_md4.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/order")
@RequiredArgsConstructor
public class OrderManagerController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK) ;
    }
    @GetMapping("/status")
    public ResponseEntity<?> getStatus(
            @RequestParam OrderStatus status
    ){

        return new ResponseEntity<>(orderService.findOrderByStatus(status), HttpStatus.OK) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
           @PathVariable Long id
    ){

        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK) ;
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) throws CustomException {
            OrderResponse orderResponse = orderService.updateOrder(id, status);
            return ResponseEntity.ok(orderResponse);

    }
}

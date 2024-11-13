package com.ra.base_project_md4.model.dto.response;

import com.ra.base_project_md4.model.constant.OrderStatus;
import com.ra.base_project_md4.model.entity.Coupon;
import com.ra.base_project_md4.model.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {

    private Long id;

    private String serialNumber;

    private User user;

    private double totalPrice;

    private OrderStatus status;

    private String note;

    private String receiveName;

    private String receiveAddress;

    private String receivePhone;

    private Coupon coupon;

    private Date createdAt;

    private Date receivedAt;
}

package com.ra.base_project_md4.model.dto.request;

import com.ra.base_project_md4.model.constant.OrderStatus;
import com.ra.base_project_md4.model.entity.Coupon;
import com.ra.base_project_md4.model.entity.User;
import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequest {
    private String serialNumber;

    private Long userId;

    private double totalPrice;

    private OrderStatus status;

    private String note;

    private String receiveName;

    private String receiveAddress;

    private String receivePhone;

    private Long couponId;

    private Date createdAt;

    private Date receivedAt;
}

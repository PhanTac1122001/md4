package com.ra.base_project_md4.model.entity;

import com.ra.base_project_md4.model.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(length = 100)
    private String note;

    @Column(length = 100)
    private String receiveName;

    @Column(length = 255)
    private String receiveAddress;

    @Column(length = 15)
    private String receivePhone;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    private Date createdAt;

    private Date receivedAt;


}

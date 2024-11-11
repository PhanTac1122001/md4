package com.ra.base_project_md4.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String couponName;

    @Column(length = 255)
    private String couponCode;

    private Integer quantity;

    private Date startDate;

    private Date endDate;

    @Column(nullable = false)
    private Boolean status;
}

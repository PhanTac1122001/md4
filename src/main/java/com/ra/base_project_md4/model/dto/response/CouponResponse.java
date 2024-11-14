package com.ra.base_project_md4.model.dto.response;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CouponResponse {
    private Long id;
    private String couponName;

    private String couponCode;

    private Integer quantity;

    private Date startDate;

    private Date endDate;

    private Boolean status;
}

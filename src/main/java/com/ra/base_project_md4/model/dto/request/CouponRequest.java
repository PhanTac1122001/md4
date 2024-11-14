package com.ra.base_project_md4.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CouponRequest {
    private String couponName;

    private String couponCode;

    private Integer quantity;

    private Date startDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date endDate;

    private Boolean status;
}

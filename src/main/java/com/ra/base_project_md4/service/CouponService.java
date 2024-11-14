package com.ra.base_project_md4.service;

import com.ra.base_project_md4.model.dto.request.CouponRequest;
import com.ra.base_project_md4.model.dto.response.CouponResponse;
import com.ra.base_project_md4.model.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CouponService {
    Page<Coupon> findByCouponCodeContaining(String couponName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect);

    CouponResponse save(CouponRequest couponRequest);

    CouponResponse update(CouponRequest couponRequest,Long id);

    Coupon findById(Long id);

    void delete(Long id);
}

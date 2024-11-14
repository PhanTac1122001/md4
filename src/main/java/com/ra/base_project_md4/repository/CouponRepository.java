package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.Coupon;
import com.ra.base_project_md4.model.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Page<Coupon> findByCouponCodeContaining(String couponName, Pageable pageable);
}

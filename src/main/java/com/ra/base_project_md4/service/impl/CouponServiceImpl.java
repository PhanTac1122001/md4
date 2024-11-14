package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.model.dto.request.CouponRequest;
import com.ra.base_project_md4.model.dto.response.ColorResponse;
import com.ra.base_project_md4.model.dto.response.CouponResponse;
import com.ra.base_project_md4.model.entity.Color;
import com.ra.base_project_md4.model.entity.Coupon;
import com.ra.base_project_md4.repository.CouponRepository;
import com.ra.base_project_md4.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    @Override
    public Page<Coupon> findByCouponCodeContaining(String couponName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect) {
        Sort sort=Sort.by(sortBy);
        sort=sortDirect.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        return couponRepository.findByCouponCodeContaining(couponName, pageable);
    }

    @Override
    public CouponResponse save(CouponRequest couponRequest) {
        Coupon coupon=Coupon.builder()
                .couponName(couponRequest.getCouponName())
                .couponCode(couponRequest.getCouponCode())
                .quantity(couponRequest.getQuantity())
                .startDate(new Date())
                .endDate(couponRequest.getEndDate())
                .status(couponRequest.getStatus())
                .build();
        Coupon couponNew=couponRepository.save(coupon);
        return CouponResponse.builder()
                .id(couponNew.getId())
                .couponCode(couponNew.getCouponCode())
                .couponName(couponNew.getCouponName())
                .quantity(couponNew.getQuantity())
                .startDate(couponNew.getStartDate())
                .endDate(couponNew.getEndDate())
                .status(couponNew.getStatus())
                .build();
    }

    @Override
    public CouponResponse update(CouponRequest couponRequest, Long id) {
        Coupon coupon=findById(id);
        if (couponRequest.getCouponCode() != null && !couponRequest.getCouponCode().isEmpty()) {
            coupon.setCouponCode(couponRequest.getCouponCode());
        }
        if (couponRequest.getCouponName() != null && !couponRequest.getCouponName().isEmpty()) {
            coupon.setCouponName(couponRequest.getCouponName());
        }
        if (couponRequest.getStatus() != null) {
            coupon.setStatus(couponRequest.getStatus());
        }
        if (couponRequest.getQuantity() != null && couponRequest.getQuantity() > 0) {
            coupon.setQuantity(couponRequest.getQuantity());
        }
        if (couponRequest.getEndDate() != null) {
            coupon.setEndDate(couponRequest.getEndDate());
        }

        Coupon couponUpdate=couponRepository.save(coupon);
        return CouponResponse.builder()
                .id(couponUpdate.getId())
                .couponName(couponUpdate.getCouponName())
                .couponCode(couponUpdate.getCouponCode())
                .quantity(couponUpdate.getQuantity())
                .endDate(couponUpdate.getEndDate())
                .startDate(couponUpdate.getStartDate())
                .status(couponUpdate.getStatus())
                .build();
    }

    @Override
    public Coupon findById(Long id) {
        return couponRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
couponRepository.deleteById(id);
    }
}

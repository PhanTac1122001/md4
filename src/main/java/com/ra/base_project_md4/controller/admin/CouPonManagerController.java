package com.ra.base_project_md4.controller.admin;

import com.ra.base_project_md4.model.dto.request.ColorRequest;
import com.ra.base_project_md4.model.dto.request.CouponRequest;
import com.ra.base_project_md4.model.dto.response.ColorResponse;
import com.ra.base_project_md4.model.dto.response.CouponResponse;
import com.ra.base_project_md4.model.entity.Color;
import com.ra.base_project_md4.model.entity.Coupon;
import com.ra.base_project_md4.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/coupon")
@RequiredArgsConstructor
public class CouPonManagerController {
    private final CouponService couponService;
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirect,
            @RequestParam(defaultValue = "", required = false) String colorName
    ) {
        Page<Coupon> coupons = couponService.findByCouponCodeContaining(colorName, pageNo, pageSize, sortBy, sortDirect);
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCouponById(@PathVariable Long id) {

        return new ResponseEntity<>(couponService.findById(id),HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<CouponResponse> save(@Valid @RequestBody CouponRequest couponRequest) {
        CouponResponse couponResponse = couponService.save(couponRequest);
        return new ResponseEntity<>(couponResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CouponResponse> update(@PathVariable Long id,@RequestBody CouponRequest couponRequest){
        CouponResponse couponUpdate = couponService.update(couponRequest,id);
        return new ResponseEntity<>(couponUpdate,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        couponService.delete(id);
        return new ResponseEntity<>("Delete Successfully",HttpStatus.NO_CONTENT);
    }
}

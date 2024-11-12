package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.model.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {
    Page<ProductDetail> findByProductDetailNameContaining(String productDetailName, Pageable pageable);
}

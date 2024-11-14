package com.ra.base_project_md4.service;

import com.ra.base_project_md4.model.dto.request.ProductDetailRequest;
import com.ra.base_project_md4.model.dto.request.ProductRequest;
import com.ra.base_project_md4.model.dto.response.ProductDetailResponse;
import com.ra.base_project_md4.model.dto.response.ProductResponse;
import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.model.entity.ProductDetail;
import org.springframework.data.domain.Page;

public interface ProductDetailService {
    Page<ProductDetail> findAll(String productDetailName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect);

    ProductDetailResponse save(ProductDetailRequest productDetailRequest,Long productId);

    ProductDetailResponse update(ProductDetailRequest productDetailRequest,Long productId);

    ProductDetail findById(Long id);

    void delete(Long id);

    void save(ProductDetail productDetail);
}

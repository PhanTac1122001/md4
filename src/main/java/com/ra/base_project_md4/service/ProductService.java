package com.ra.base_project_md4.service;

import com.ra.base_project_md4.model.dto.request.CategoryRequest;
import com.ra.base_project_md4.model.dto.request.ProductRequest;
import com.ra.base_project_md4.model.dto.response.CategoryResponse;
import com.ra.base_project_md4.model.dto.response.ProductResponse;
import com.ra.base_project_md4.model.entity.Category;
import com.ra.base_project_md4.model.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> findAll(String productName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect);

    ProductResponse save(ProductRequest productRequest);

    ProductResponse update(ProductRequest productRequest,Long productId);

    Product findById(Long id);

    void delete(Long id);

}

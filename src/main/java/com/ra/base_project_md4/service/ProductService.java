package com.ra.base_project_md4.service;

import com.ra.base_project_md4.model.dto.request.CategoryRequest;
import com.ra.base_project_md4.model.dto.request.ProductRequest;
import com.ra.base_project_md4.model.dto.response.CategoryResponse;
import com.ra.base_project_md4.model.dto.response.ProductResponse;
import com.ra.base_project_md4.model.dto.response.SaleCategoryResponse;
import com.ra.base_project_md4.model.entity.Category;
import com.ra.base_project_md4.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> findAll(String productName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect);

    ProductResponse save(ProductRequest productRequest);

    ProductResponse update(ProductRequest productRequest,Long productId);

    Product findById(Long id);

    void delete(Long id);

    List<Product> getNewProducts(int page, int size);

    Page<Product> findByProductNameContainingAndStatus(String productName, boolean status, Integer pageNo, Integer pageSize, String sortBy, String sortDirect);
    List<Product> isStatus();
    List<Product> getFeaturedProducts();
    List<Product> getBestSellingProducts(int topOrder);
}

package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.advice.ControllerAdvice;
import com.ra.base_project_md4.model.dto.error.DataError;
import com.ra.base_project_md4.model.dto.error.DataErrorException;
import com.ra.base_project_md4.model.dto.request.CategoryRequest;
import com.ra.base_project_md4.model.dto.request.ProductRequest;
import com.ra.base_project_md4.model.dto.response.CategoryResponse;
import com.ra.base_project_md4.model.dto.response.ProductResponse;
import com.ra.base_project_md4.model.entity.Category;
import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.repository.CategoryRepository;
import com.ra.base_project_md4.repository.ProductRepository;
import com.ra.base_project_md4.service.CategoryService;
import com.ra.base_project_md4.service.ProductService;
import com.ra.base_project_md4.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UploadService uploadService;


    @Override
    public Page<Product> findAll(String productName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect) {
        Sort sort = Sort.by(sortBy);
        sort = sortDirect.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return productRepository.findByProductNameContaining(productName, pageable);
    }

    @Override
    public ProductResponse save(ProductRequest productRequest) {
        String imgFile = uploadService.uploadFile(productRequest.getImage());
        Category category = categoryService.findById(productRequest.getCategoryId());

        String sku = UUID.randomUUID().toString();
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .category(category)
                .description(productRequest.getDescription())
                .createdAt(new Date())
                .updatedAt(new Date())
                .sku(sku)
                .image(imgFile)
                .status(productRequest.getStatus())
                .build();
        Product productNew = productRepository.save(product);
        return ProductResponse.builder()
                .id(productNew.getId())
                .productName(productNew.getProductName())
                .categoryId(category.getId())
                .description(productRequest.getDescription())
                .createdAt(new Date())
                .updatedAt(new Date())
                .image(imgFile)
                .status(productRequest.getStatus())
                .build();
    }

    @Override
    public ProductResponse update(ProductRequest productRequest, Long productId) {


        Category category = categoryService.findById(productRequest.getCategoryId());

        Product product = findById(productId);
        if (productRequest.getImage() != null) {
            String imgFile = uploadService.uploadFile(productRequest.getImage());
            product.setImage(imgFile);
        }
        product.setProductName(productRequest.getProductName());
        product.setCategory(category);
        product.setDescription(productRequest.getDescription());
        product.setStatus(productRequest.getStatus());
        product.setUpdatedAt(new Date());

        Product productUpdate = productRepository.save(product);
        return ProductResponse.builder()
                .id(productUpdate.getId())
                .productName(productUpdate.getProductName())
                .description(productUpdate.getDescription())
                .categoryId(category.getId())
                .image(productUpdate.getImage())
                .updatedAt(productUpdate.getUpdatedAt())
                .sku(productUpdate.getSku())
                .status(productUpdate.getStatus())
                .build();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
    productRepository.deleteById(id);
    }
}

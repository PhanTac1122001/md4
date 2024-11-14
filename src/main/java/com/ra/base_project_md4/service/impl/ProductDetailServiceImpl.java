package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.model.dto.request.ProductDetailRequest;
import com.ra.base_project_md4.model.dto.request.ProductRequest;
import com.ra.base_project_md4.model.dto.response.ProductDetailResponse;
import com.ra.base_project_md4.model.dto.response.ProductResponse;
import com.ra.base_project_md4.model.entity.*;
import com.ra.base_project_md4.repository.ProductDetailRepository;
import com.ra.base_project_md4.repository.ProductRepository;
import com.ra.base_project_md4.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductService productService;
    private final SizeService sizeService;
    private final ColorService colorService;
    private final UploadService uploadService;
    private final ProductDetailRepository productDetailRepository;

    @Override
    public Page<ProductDetail> findAll(String productDetailName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect) {
        Sort sort = Sort.by(sortBy);
        sort = sortDirect.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return productDetailRepository.findByProductDetailNameContaining(productDetailName, pageable);
    }

    @Override
    public ProductDetailResponse save(ProductDetailRequest productDetailRequest,Long productId) {
        String imgFile = uploadService.uploadFile(productDetailRequest.getImage());
        Product product = productService.findById(productId);
        Size size=sizeService.findById(productDetailRequest.getSizeId());
        Color color=colorService.findById(productDetailRequest.getColorId());

        ProductDetail productDetail = ProductDetail.builder()
                .productDetailName(productDetailRequest.getProductDetailName())
                .product(product)
                .color(color)
                .size(size)
                .stock(productDetailRequest.getStock())
                .price(productDetailRequest.getPrice())
                .image(imgFile)
                .status(productDetailRequest.getStatus())
                .build();
        ProductDetail productDetailNew = productDetailRepository.save(productDetail);
        return ProductDetailResponse.builder()
                .id(productDetailNew.getId())
                .productDetailName(productDetailNew.getProductDetailName())
                .product(product)
                .color(color)
                .stock(productDetailNew.getStock())
                .price(productDetailNew.getPrice())
                .size(size)
                .image(imgFile)
                .status(productDetailRequest.getStatus())
                .build();

    }

    @Override
    public ProductDetailResponse update(ProductDetailRequest productDetailRequest, Long productDetailId) {

        Size size = sizeService.findById(productDetailRequest.getSizeId());
        Color color = colorService.findById(productDetailRequest.getColorId());

        ProductDetail productDetail = findById(productDetailId);
        String imgFile = null;
        if (productDetailRequest.getImage() != null) {
            imgFile = uploadService.uploadFile(productDetailRequest.getImage());
            productDetail.setImage(imgFile);
        }
        productDetail.setProductDetailName(productDetailRequest.getProductDetailName());
        productDetail.setColor(color);
        productDetail.setPrice(productDetailRequest.getPrice());
        productDetail.setSize(size);
        productDetail.setStock(productDetailRequest.getStock());
        productDetail.setImage(imgFile);
        productDetail.setStatus(productDetailRequest.getStatus());

        ProductDetail productDetailUpdate = productDetailRepository.save(productDetail);
        return ProductDetailResponse.builder()
                .id(productDetailUpdate.getId())
                .productDetailName(productDetailUpdate.getProductDetailName())
                .color(productDetailUpdate.getColor())
                .size(productDetailUpdate.getSize())
                .image(productDetailUpdate.getImage())
                .stock(productDetailUpdate.getStock())
                .price(productDetailUpdate.getPrice())
                .product(productDetailUpdate.getProduct())
                .status(productDetailUpdate.getStatus())
                .build();
    }

    @Override
    public ProductDetail findById(Long id) {
        return productDetailRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        productDetailRepository.deleteById(id);
    }

    @Override
    public void save(ProductDetail productDetail) {
        productDetailRepository.save(productDetail);
    }
}

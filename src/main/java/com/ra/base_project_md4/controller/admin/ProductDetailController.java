package com.ra.base_project_md4.controller.admin;

import com.ra.base_project_md4.model.dto.request.ProductDetailRequest;
import com.ra.base_project_md4.model.dto.request.ProductRequest;
import com.ra.base_project_md4.model.dto.response.ProductDetailResponse;
import com.ra.base_project_md4.model.dto.response.ProductResponse;
import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.model.entity.ProductDetail;
import com.ra.base_project_md4.service.CategoryService;
import com.ra.base_project_md4.service.ProductDetailService;
import com.ra.base_project_md4.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/product-detail")
@RequiredArgsConstructor
public class ProductDetailController {
    private final ProductDetailService productDetailService;
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirect,
            @RequestParam(defaultValue = "", required = false) String categoryName
    ) {
        Page<ProductDetail> productDetails = productDetailService.findAll(categoryName, pageNo, pageSize, sortBy, sortDirect);
        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {

        return new ResponseEntity<>(productDetailService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<ProductDetailResponse> save(@Valid @PathVariable Long id ,@ModelAttribute ProductDetailRequest productDetailRequest) {
        ProductDetailResponse productDetailResponse = productDetailService.save(productDetailRequest,id);
        return new ResponseEntity<>(productDetailResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailResponse> update(@Valid @PathVariable Long id,@ModelAttribute ProductDetailRequest productDetailRequest){
        ProductDetailResponse productDetailResponse = productDetailService.update(productDetailRequest,id);
        return new ResponseEntity<>(productDetailResponse,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>("Delete Successfully",HttpStatus.NO_CONTENT);
    }
}

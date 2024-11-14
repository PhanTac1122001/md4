package com.ra.base_project_md4.controller.admin;

import com.ra.base_project_md4.model.dto.request.CategoryRequest;
import com.ra.base_project_md4.model.dto.request.ProductRequest;
import com.ra.base_project_md4.model.dto.response.CategoryResponse;
import com.ra.base_project_md4.model.dto.response.ProductResponse;
import com.ra.base_project_md4.model.entity.Category;
import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.service.CategoryService;
import com.ra.base_project_md4.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/product")
@RequiredArgsConstructor
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirect,
            @RequestParam(defaultValue = "", required = false) String productSearch
    ) {
        Page<Product> products = productService.findAll(productSearch, pageNo, pageSize, sortBy, sortDirect);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {

        return new ResponseEntity<>(productService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> save(@Valid @ModelAttribute ProductRequest productRequest) {
        ProductResponse productResponse = productService.save(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@Valid @PathVariable Long id,@ModelAttribute ProductRequest productRequest){
        ProductResponse productUpdate = productService.update(productRequest,id);
        return new ResponseEntity<>(productUpdate,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>("Delete Successfully",HttpStatus.NO_CONTENT);
    }
}

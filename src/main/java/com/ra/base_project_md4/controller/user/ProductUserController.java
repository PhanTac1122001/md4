package com.ra.base_project_md4.controller.user;

import com.ra.base_project_md4.model.dto.response.ProductResponse;
import com.ra.base_project_md4.model.dto.response.SaleCategoryResponse;
import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.security.UserPrinciple;
import com.ra.base_project_md4.service.ProductService;
import com.ra.base_project_md4.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/product")
@RequiredArgsConstructor
public class ProductUserController {
    private final ProductService productService;
    private final WishListService wishListService;
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
    @GetMapping("/new")
    public ResponseEntity<List<Product>> getNewProducts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(productService.getNewProducts(page, size),HttpStatus.OK) ;
    }
    @GetMapping("/status")
    public ResponseEntity<?> isStatus( @RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                       @RequestParam(required = false, defaultValue = "true") boolean status,
                                       @RequestParam(defaultValue = "id") String sortBy,
                                       @RequestParam(defaultValue = "asc") String sortDirect,
                                       @RequestParam(defaultValue = "", required = false) String productSearch) {

        Page<Product> products=productService.findByProductNameContainingAndStatus(productSearch,status,pageNo,pageSize,sortBy,sortDirect);
        return new ResponseEntity<>(products, HttpStatus.OK);

    }
    @GetMapping("/featured")
    public ResponseEntity<?> getFeaturedProducts() {
        List<Product> featuredProducts = productService.getFeaturedProducts();

        return new ResponseEntity<>(featuredProducts,HttpStatus.OK);
    }
    @GetMapping("/best-sellers")
    public ResponseEntity<List<Product>> getBestSellingProducts(@RequestParam(value = "topN", defaultValue = "10") int topN) {
        try {

            List<Product> bestSellingProducts = productService.getBestSellingProducts(topN);

            if (bestSellingProducts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bestSellingProducts, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

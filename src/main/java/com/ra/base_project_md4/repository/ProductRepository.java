package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.Color;
import com.ra.base_project_md4.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);


}

package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.Color;
import com.ra.base_project_md4.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    Page<Product> findAllByOrderByCreatedAtDesc(Pageable pageable);

    List<Product> findByStatus(boolean status);
    Page<Product> findByProductNameContainingAndStatus(String productName, boolean status, Pageable pageable);

}

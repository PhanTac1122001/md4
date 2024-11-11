package com.ra.base_project_md4.repository;


import com.ra.base_project_md4.model.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Long> {
    Page<Size> findBySizeNameContaining(String sizeName, Pageable pageable);
}

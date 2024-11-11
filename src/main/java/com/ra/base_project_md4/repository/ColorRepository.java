package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,Long> {
    Page<Color> findByColorNameContaining(String colorName, Pageable pageable);


}

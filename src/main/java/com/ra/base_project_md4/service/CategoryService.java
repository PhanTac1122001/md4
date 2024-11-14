package com.ra.base_project_md4.service;

import com.ra.base_project_md4.model.dto.request.CategoryRequest;
import com.ra.base_project_md4.model.dto.request.ColorRequest;
import com.ra.base_project_md4.model.dto.response.CategoryResponse;
import com.ra.base_project_md4.model.dto.response.ColorResponse;
import com.ra.base_project_md4.model.dto.response.SaleCategoryResponse;
import com.ra.base_project_md4.model.entity.Category;
import com.ra.base_project_md4.model.entity.Color;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Page<Category> findAll(String categoryName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect);

    CategoryResponse save(CategoryRequest categoryRequest);

    CategoryResponse update(CategoryRequest categoryRequest,Long categoryId);

    Category findById(Long id);

    void delete(Long id);

    List<SaleCategoryResponse> isStatus();
}

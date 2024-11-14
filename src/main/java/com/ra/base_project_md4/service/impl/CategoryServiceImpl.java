package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.model.dto.request.CategoryRequest;
import com.ra.base_project_md4.model.dto.response.CategoryResponse;
import com.ra.base_project_md4.model.dto.response.ColorResponse;
import com.ra.base_project_md4.model.dto.response.SaleCategoryResponse;
import com.ra.base_project_md4.model.entity.Category;
import com.ra.base_project_md4.model.entity.Color;
import com.ra.base_project_md4.model.entity.User;
import com.ra.base_project_md4.repository.CategoryRepository;
import com.ra.base_project_md4.repository.ColorRepository;
import com.ra.base_project_md4.service.CategoryService;
import com.ra.base_project_md4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    @Override
    public Page<Category> findAll(String colorName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect) {
        Sort sort=Sort.by(sortBy);
        sort=sortDirect.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        return categoryRepository.findByCategoryNameContaining(colorName, pageable);
    }

    @Override
    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category=Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .description(categoryRequest.getDescription())
                .status(categoryRequest.getStatus())
                .build();
        Category categoryNew=categoryRepository.save(category);
        return CategoryResponse.builder()
                .id(categoryNew.getId())
                .categoryName(categoryNew.getCategoryName())
                .description(categoryNew.getDescription())
                .status(categoryNew.getStatus())
                .build();
    }

    @Override
    public CategoryResponse update(CategoryRequest categoryRequest, Long categoryId) {
        Category category=findById(categoryId);
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setDescription(categoryRequest.getDescription());
        category.setStatus(categoryRequest.getStatus());
        Category categoryUpdate=categoryRepository.save(category);
        return CategoryResponse.builder()
                .id(categoryUpdate.getId())
                .categoryName(categoryUpdate.getCategoryName())
                .description(categoryUpdate.getDescription())
                .status(categoryUpdate.getStatus())
                .build();
    }

    @Override
    public Category findById(Long id) {
        Category category=categoryRepository.findById(id).orElseThrow();
        if (category != null) {
            return category;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
categoryRepository.deleteById(id);
    }

    @Override
    public List<SaleCategoryResponse> isStatus() {
        List<Category> categories=categoryRepository.findAll();
        List<SaleCategoryResponse> saleCategories = categories.stream()
                .filter(Category::getStatus)
                .map(category -> SaleCategoryResponse.builder()
                        .id(category.getId())
                        .categoryName(category.getCategoryName())
                        .description(category.getDescription())
                        .status(category.getStatus())
                        .build())
                .collect(Collectors.toList());


        if (saleCategories.isEmpty()) {
            return null;
        }
        return saleCategories;
    }
}

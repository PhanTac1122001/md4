package com.ra.base_project_md4.controller.admin;

import com.ra.base_project_md4.model.dto.request.CategoryRequest;
import com.ra.base_project_md4.model.dto.request.ColorRequest;
import com.ra.base_project_md4.model.dto.response.CategoryResponse;
import com.ra.base_project_md4.model.dto.response.ColorResponse;
import com.ra.base_project_md4.model.entity.Category;
import com.ra.base_project_md4.model.entity.Color;
import com.ra.base_project_md4.service.CategoryService;
import com.ra.base_project_md4.service.ColorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirect,
            @RequestParam(defaultValue = "", required = false) String categoryName
    ) {
        Page<Category> categories = categoryService.findAll(categoryName, pageNo, pageSize, sortBy, sortDirect);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {

        return new ResponseEntity<>(categoryService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.save(categoryRequest);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@Valid @PathVariable Long id,@RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryUpdate = categoryService.update(categoryRequest,id);
        return new ResponseEntity<>(categoryUpdate,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoryService.delete(id);
        return new ResponseEntity<>("Delete Successfully",HttpStatus.NO_CONTENT);
    }
}

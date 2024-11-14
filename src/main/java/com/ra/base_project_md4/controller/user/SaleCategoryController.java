package com.ra.base_project_md4.controller.user;

import com.ra.base_project_md4.model.dto.response.SaleCategoryResponse;
import com.ra.base_project_md4.model.entity.Order;
import com.ra.base_project_md4.security.UserPrinciple;
import com.ra.base_project_md4.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/sale")
@RequiredArgsConstructor
public class SaleCategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<SaleCategoryResponse>> findAll() {

        return new ResponseEntity<>(categoryService.isStatus(), HttpStatus.OK);

    }
}

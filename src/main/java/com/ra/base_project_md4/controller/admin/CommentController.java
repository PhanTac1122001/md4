package com.ra.base_project_md4.controller.admin;

import com.ra.base_project_md4.model.dto.request.CouponRequest;
import com.ra.base_project_md4.model.dto.response.CouponResponse;
import com.ra.base_project_md4.model.entity.Comment;
import com.ra.base_project_md4.model.entity.Coupon;
import com.ra.base_project_md4.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirect,
            @RequestParam(defaultValue = "", required = false) String content
    ) {
        Page<Comment> comments = commentService.findByContentContaining(content, pageNo, pageSize, sortBy, sortDirect);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


}

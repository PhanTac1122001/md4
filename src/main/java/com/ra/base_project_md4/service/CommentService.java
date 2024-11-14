package com.ra.base_project_md4.service;

import com.ra.base_project_md4.model.dto.request.CommentRequest;
import com.ra.base_project_md4.model.dto.request.CouponRequest;
import com.ra.base_project_md4.model.dto.response.CommentResponse;
import com.ra.base_project_md4.model.dto.response.CouponResponse;
import com.ra.base_project_md4.model.entity.Comment;
import com.ra.base_project_md4.model.entity.Coupon;
import org.springframework.data.domain.Page;

public interface CommentService {
    Page<Comment> findByContentContaining(String content, Integer pageNo, Integer pageSize, String sortBy, String sortDirect);



}

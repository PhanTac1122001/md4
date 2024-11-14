package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.model.dto.request.ColorRequest;
import com.ra.base_project_md4.model.dto.request.CommentRequest;
import com.ra.base_project_md4.model.dto.response.ColorResponse;
import com.ra.base_project_md4.model.dto.response.CommentResponse;
import com.ra.base_project_md4.model.dto.response.CouponResponse;
import com.ra.base_project_md4.model.entity.*;
import com.ra.base_project_md4.repository.ColorRepository;
import com.ra.base_project_md4.repository.CommentRepository;
import com.ra.base_project_md4.service.ColorService;
import com.ra.base_project_md4.service.CommentService;
import com.ra.base_project_md4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    @Override
    public Page<Comment> findByContentContaining(String content, Integer pageNo, Integer pageSize, String sortBy, String sortDirect) {
        Sort sort=Sort.by(sortBy);
        sort=sortDirect.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        return commentRepository.findByContentContaining(content, pageable);
    }


}

package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.Comment;
import com.ra.base_project_md4.model.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findByContentContaining(String content, Pageable pageable);
}

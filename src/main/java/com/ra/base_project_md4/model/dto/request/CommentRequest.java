package com.ra.base_project_md4.model.dto.request;

import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.model.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentRequest {
    private Long id;

    private String content;

    private Integer rating;

    private Long userId;

    private Long productId;

    private Long parentId;

    private Boolean status;
}

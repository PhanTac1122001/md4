package com.ra.base_project_md4.model.dto.response;

import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentResponse {

    private Long id;

    private String content;

    private Integer rating;


    private User user;

    private Product product;

    private Long parentId;

    private Boolean status;
}

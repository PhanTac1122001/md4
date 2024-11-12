package com.ra.base_project_md4.model.dto.response;

import com.ra.base_project_md4.model.entity.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {

    private Long id;

    private String sku;

    private String productName;


    private String description;

    private String image;

    private Long categoryId;

    private Date createdAt;

    private Date updatedAt;

    private Boolean status;
}

package com.ra.base_project_md4.model.dto.request;

import com.ra.base_project_md4.model.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {

    private String productName;


    private String description;


    private MultipartFile image;


    private Long categoryId;


    private Date createdAt;

    private Date updatedAt;

    private Boolean status;
}

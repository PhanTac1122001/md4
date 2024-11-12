package com.ra.base_project_md4.model.dto.request;

import com.ra.base_project_md4.model.entity.Color;
import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.model.entity.Size;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductDetailRequest {

    private String productDetailName;

    private Double price;

    private Integer stock;

    private MultipartFile image;

    private Long productId;


    private Long colorId;


    private Long sizeId;

    private Boolean status;
}

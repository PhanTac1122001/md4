package com.ra.base_project_md4.model.dto.response;

import com.ra.base_project_md4.model.entity.Color;
import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.model.entity.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductDetailResponse {
    private Long id;

    private String productDetailName;

    private Double price;

    private Integer stock;

    private String image;

    private Product product;

    private Color color;

    private Size size;

    private Boolean status;
}

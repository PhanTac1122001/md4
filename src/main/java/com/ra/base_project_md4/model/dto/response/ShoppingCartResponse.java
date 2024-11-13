package com.ra.base_project_md4.model.dto.response;

import com.ra.base_project_md4.model.entity.ProductDetail;
import com.ra.base_project_md4.model.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCartResponse {
    private Long id;

    private ProductDetail productDetail;


    private User user;

    private Integer quantity;
}

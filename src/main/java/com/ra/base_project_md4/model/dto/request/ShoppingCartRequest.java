package com.ra.base_project_md4.model.dto.request;

import com.ra.base_project_md4.model.entity.ProductDetail;
import com.ra.base_project_md4.model.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCartRequest {


    private Long productDetailId;

    private Long userId;

    private Integer quantity;
}

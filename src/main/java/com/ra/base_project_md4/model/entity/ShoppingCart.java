package com.ra.base_project_md4.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "shoping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_detail_id", nullable = false)
    private ProductDetail productDetail;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Integer quantity;
}

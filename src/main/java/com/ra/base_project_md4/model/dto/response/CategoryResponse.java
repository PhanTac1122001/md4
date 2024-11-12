package com.ra.base_project_md4.model.dto.response;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {
    private Long id;

    private String categoryName;

    private String description;

    private Boolean status;
}

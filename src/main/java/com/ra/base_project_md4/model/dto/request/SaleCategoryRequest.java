package com.ra.base_project_md4.model.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SaleCategoryRequest {
    private String categoryName;

    private String description;

    private Boolean status;
}

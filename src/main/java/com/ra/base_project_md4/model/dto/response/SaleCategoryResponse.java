package com.ra.base_project_md4.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SaleCategoryResponse {
    private Long id;

    private String categoryName;

    private String description;

    private Boolean status;
}

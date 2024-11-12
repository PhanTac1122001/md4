package com.ra.base_project_md4.model.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryRequest {

    private String categoryName;

    private String description;

    private Boolean status;
}

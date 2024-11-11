package com.ra.base_project_md4.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SizeResponse {

    private Long id;

    private String sizeName;

    private Boolean status;
}

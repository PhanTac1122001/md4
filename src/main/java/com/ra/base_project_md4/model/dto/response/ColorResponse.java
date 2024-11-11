package com.ra.base_project_md4.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ColorResponse {
    private Long id;


    private String colorName;


    private String colorCode;


    private Boolean status;

}

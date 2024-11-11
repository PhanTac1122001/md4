package com.ra.base_project_md4.model.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ColorRequest {

    private String colorName;


    private String colorCode;


    private Boolean status;


}

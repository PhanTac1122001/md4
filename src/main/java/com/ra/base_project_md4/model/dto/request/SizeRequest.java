package com.ra.base_project_md4.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SizeRequest {
    @NotBlank(message = "Không được rỗng")
    private String sizeName;
    private Boolean status;
}

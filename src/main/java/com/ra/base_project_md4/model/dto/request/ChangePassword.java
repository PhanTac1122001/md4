package com.ra.base_project_md4.model.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChangePassword {
    private String oldPass;

    private String newPass;

    private String confirmNewPass;
}

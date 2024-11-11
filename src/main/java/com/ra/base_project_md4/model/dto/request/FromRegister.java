package com.ra.base_project_md4.model.dto.request;



import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FromRegister {
    @NotBlank

    private String username;

    private String fullName;

    private String password;

}

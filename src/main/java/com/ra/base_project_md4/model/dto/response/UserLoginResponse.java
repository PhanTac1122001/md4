package com.ra.base_project_md4.model.dto.response;

import com.ra.base_project_md4.model.entity.Role;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginResponse {
    private String username;
    private String accessToken;
    private String typeToken;
    private Set<Role> roles;
}

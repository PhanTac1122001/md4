package com.ra.base_project_md4.model.dto.response;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {

    private Long id;
    private String username;

    private String email;

    private String fullName;

    private Boolean status;

    private String avatar;

    private String phone;

    private String address;

    private Date updatedAt;


}

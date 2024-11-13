package com.ra.base_project_md4.model.dto.request;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {

    private String email;

    private String fullName;



    private MultipartFile avatar;

    private String phone;

    private String address;



}

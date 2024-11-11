package com.ra.base_project_md4.service;

import com.ra.base_project_md4.model.dto.request.FromRegister;
import com.ra.base_project_md4.model.dto.request.UserLoginRequest;
import com.ra.base_project_md4.model.dto.response.UserLoginResponse;

public interface AuthService {
    UserLoginResponse login(UserLoginRequest userLoginRequest);

    void register(FromRegister fromRegister);
}

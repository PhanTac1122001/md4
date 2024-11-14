package com.ra.base_project_md4.service;


import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.dto.request.ChangePassword;
import com.ra.base_project_md4.model.dto.request.UserRequest;
import com.ra.base_project_md4.model.dto.response.UserResponse;
import com.ra.base_project_md4.model.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<User> findAll(String username, Integer pageNo, Integer pageSize, String sortBy, String sortDirect);

    UserResponse update(UserRequest userRequest,Long userId) throws CustomException;

    User changeStatus(Long userId);

    User findById(Long id);

    User changePass(ChangePassword changePassword, Long userId) throws CustomException;
}

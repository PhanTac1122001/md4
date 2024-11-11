package com.ra.base_project_md4.service;


import com.ra.base_project_md4.model.dto.request.UserRequest;
import com.ra.base_project_md4.model.dto.response.UserResponse;
import com.ra.base_project_md4.model.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<User> findAll(String username, Integer pageNo, Integer pageSize, String sortBy, String sortDirect);

    UserResponse update(UserRequest userRequest,Long userId);

    User findById(Long id);
}

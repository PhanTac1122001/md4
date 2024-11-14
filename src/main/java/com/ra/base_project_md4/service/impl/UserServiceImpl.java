package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.constant.RoleName;
import com.ra.base_project_md4.model.dto.request.ChangePassword;
import com.ra.base_project_md4.model.dto.request.UserRequest;
import com.ra.base_project_md4.model.dto.response.SizeResponse;
import com.ra.base_project_md4.model.dto.response.UserResponse;
import com.ra.base_project_md4.model.entity.Role;
import com.ra.base_project_md4.model.entity.Size;
import com.ra.base_project_md4.model.entity.User;
import com.ra.base_project_md4.repository.UserRepository;
import com.ra.base_project_md4.service.RoleService;
import com.ra.base_project_md4.service.UploadService;
import com.ra.base_project_md4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UploadService uploadService;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<User> findAll(String username, Integer pageNo, Integer pageSize, String sortBy, String sortDirect) {
        Sort sort=Sort.by(sortBy);
        sort=sortDirect.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        return userRepository.findByUsernameContaining(username, pageable);
    }

    @Override
    public User changeStatus(Long userId) {
        User user=findById(userId);
        Set<Role> roles = user.getRoles();
        boolean isAdmin = roles.stream()
                .anyMatch(role -> role.getRoleName() == RoleName.ADMIN);
        if (isAdmin) {
            return null;
        }
        user.setStatus(!user.getStatus());
            return  userRepository.save(user);

    }

    @Override
    public UserResponse update(UserRequest userRequest, Long userId) throws CustomException {

        User user = findById(userId);
        if (user == null) {
            throw new CustomException("User not found with id: " + userId);
        }


        String imgFile = null;
        if (userRequest.getAvatar() != null && !userRequest.getAvatar().isEmpty()) {
            imgFile = uploadService.uploadFile(userRequest.getAvatar());
            user.setAvatar(imgFile);
        }

        if (userRequest.getAddress() != null && !userRequest.getAddress().isEmpty()) {
            user.setAddress(userRequest.getAddress());
        }
        if (userRequest.getPhone() != null && !userRequest.getPhone().isEmpty()) {
            user.setPhone(userRequest.getPhone());
        }
        if (userRequest.getFullName() != null && !userRequest.getFullName().isEmpty()) {
            user.setFullName(userRequest.getFullName());
        }
        if (userRequest.getEmail() != null && !userRequest.getEmail().isEmpty()) {
            user.setEmail(userRequest.getEmail());
        }


        user.setUpdatedAt(new Date());

        User userUpdate = userRepository.save(user);

        return UserResponse.builder()
                .id(userUpdate.getId())
                .username(userUpdate.getUsername())
                .phone(userUpdate.getPhone())
                .avatar(userUpdate.getAvatar())
                .email(userUpdate.getEmail())
                .address(userUpdate.getAddress())
                .fullName(userUpdate.getFullName())
                .status(userUpdate.getStatus())
                .updatedAt(userUpdate.getUpdatedAt())
                .build();
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User changePass(ChangePassword changePassword, Long userId) throws CustomException {
        User user = findById(userId);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with id: " + userId);
        }

        if (!passwordEncoder.matches(changePassword.getOldPass(), user.getPassword())) {
            throw new CustomException("Old password is incorrect.");
        }

        if (!changePassword.getNewPass().equals(changePassword.getConfirmNewPass())) {
            throw new CustomException("New password and confirm password do not match.");
        }

        user.setPassword(passwordEncoder.encode(changePassword.getNewPass()));
        user.setUpdatedAt(new Date());

        return userRepository.save(user);
    }
}

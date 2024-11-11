package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.model.constant.RoleName;
import com.ra.base_project_md4.model.dto.request.FromRegister;
import com.ra.base_project_md4.model.dto.request.UserLoginRequest;
import com.ra.base_project_md4.model.dto.response.UserLoginResponse;
import com.ra.base_project_md4.model.entity.Role;
import com.ra.base_project_md4.model.entity.User;
import com.ra.base_project_md4.repository.UserRepository;
import com.ra.base_project_md4.security.UserPrinciple;
import com.ra.base_project_md4.security.jwt.JwtProvider;
import com.ra.base_project_md4.service.AuthService;
import com.ra.base_project_md4.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationProvider authenticationProvider;
    private final JwtProvider jwtProvider;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    public void register(FromRegister fromRegister) {
        Set<Role> roles=new HashSet<>();

        roles.add(roleService.findRoleByRoleName(RoleName.USER));
        User user=User.builder()
                .fullName(fromRegister.getFullName())
                .username(fromRegister.getUsername())
                .password(passwordEncoder.encode(fromRegister.getPassword()))
                .roles(roles)
                .status(true)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        userRepository.save(user);
    }


    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        Authentication authentication;
        authentication =authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(),userLoginRequest.getPassword()));
        UserPrinciple userPrinciple= (UserPrinciple) authentication.getPrincipal();
        String token =jwtProvider.generateToken(userPrinciple);
        return UserLoginResponse.builder()
                .username(userPrinciple.getUsername())
                .accessToken(token)
                .typeToken("Bearer")
                .roles(userPrinciple.getUser().getRoles())
                .build();
    }
}

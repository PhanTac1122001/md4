package com.ra.base_project_md4.controller;

import com.ra.base_project_md4.model.dto.request.FromRegister;
import com.ra.base_project_md4.model.dto.request.UserLoginRequest;
import com.ra.base_project_md4.model.dto.response.UserLoginResponse;
import com.ra.base_project_md4.service.AuthService;

import com.ra.base_project_md4.service.TokenBlackListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private TokenBlackListService tokenBlackListService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        return new ResponseEntity<>(authService.login(userLoginRequest), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody FromRegister fromRegister){
        authService.register(fromRegister);
        return new ResponseEntity<>("Register Successfully", HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        if (token != null) {
            tokenBlackListService.addTokenToBlackList(token);
        }
        return new ResponseEntity<>("Logout Successfully", HttpStatus.OK);
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}

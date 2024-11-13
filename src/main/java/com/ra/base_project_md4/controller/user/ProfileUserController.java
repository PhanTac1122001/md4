package com.ra.base_project_md4.controller.user;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.dto.request.ChangePassword;
import com.ra.base_project_md4.model.dto.request.UserRequest;
import com.ra.base_project_md4.model.dto.response.UserResponse;
import com.ra.base_project_md4.model.entity.User;
import com.ra.base_project_md4.security.UserPrinciple;
import com.ra.base_project_md4.service.TokenBlackListService;
import com.ra.base_project_md4.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/profile")
@RequiredArgsConstructor
public class ProfileUserController {
    private final UserService userService;

    private final TokenBlackListService tokenBlackListService;
    @GetMapping
    public ResponseEntity<?> getUserById(@AuthenticationPrincipal UserPrinciple userPrinciple) {
        Long userId=userPrinciple.getUser().getId();
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<UserResponse> update(@AuthenticationPrincipal UserPrinciple userPrinciple, @ModelAttribute UserRequest userRequest){
        Long userId=userPrinciple.getUser().getId();
        UserResponse userUpdate = userService.update(userRequest,userId);
        return new ResponseEntity<>(userUpdate,HttpStatus.OK);
    }
    @PostMapping("/change-password")
    public ResponseEntity<?> changePass(HttpServletRequest request,@AuthenticationPrincipal UserPrinciple userPrinciple, @RequestBody ChangePassword changePassword) throws CustomException {
        Long userId=userPrinciple.getUser().getId();
        userService.changePass(changePassword,userId);
        String token = tokenBlackListService.getTokenFromRequest(request);
        if (token != null) {
            tokenBlackListService.addTokenToBlackList(token);
        }
        return new ResponseEntity<>("Change Password Successfully",HttpStatus.OK);
    }


}

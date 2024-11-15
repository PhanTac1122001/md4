package com.ra.base_project_md4.controller.admin;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.dto.request.UserRequest;
import com.ra.base_project_md4.model.dto.response.UserResponse;
import com.ra.base_project_md4.model.entity.User;
import com.ra.base_project_md4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirect,
            @RequestParam(defaultValue = "", required = false) String sizeName
    ) {
        Page<User> users = userService.findAll(sizeName, pageNo, pageSize, sortBy, sortDirect);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @ModelAttribute UserRequest userRequest) throws CustomException {
        UserResponse userUpdate = userService.update(userRequest,id);
        return new ResponseEntity<>(userUpdate,HttpStatus.OK);

    }
    @PatchMapping("/{id}")
    public ResponseEntity<User> changeStatus(@PathVariable Long id){
        User userUpdate = userService.changeStatus(id);
        return new ResponseEntity<>(userUpdate,HttpStatus.OK);

    }
}

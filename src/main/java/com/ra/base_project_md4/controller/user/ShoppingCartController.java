package com.ra.base_project_md4.controller.user;

import com.ra.base_project_md4.exception.CustomException;
import com.ra.base_project_md4.model.dto.request.ShoppingCartRequest;
import com.ra.base_project_md4.model.dto.request.UserRequest;
import com.ra.base_project_md4.model.dto.response.ShoppingCartResponse;
import com.ra.base_project_md4.model.dto.response.UserResponse;
import com.ra.base_project_md4.model.entity.ShoppingCart;
import com.ra.base_project_md4.security.UserPrinciple;
import com.ra.base_project_md4.service.ShoppingCartService;
import com.ra.base_project_md4.service.TokenBlackListService;
import com.ra.base_project_md4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    @GetMapping
    public ResponseEntity<List<ShoppingCart>> findAll(@AuthenticationPrincipal UserPrinciple userPrinciple) {
        Long userId=userPrinciple.getUser().getId();
        return new ResponseEntity<>(shoppingCartService.findAllCartByUser(userId), HttpStatus.OK);

    }
    @PostMapping("/add-cart")
    public ResponseEntity<?> getShoppingCartById(@AuthenticationPrincipal UserPrinciple userPrinciple, @RequestBody ShoppingCartRequest shoppingCartRequest) {
        Long userId=userPrinciple.getUser().getId();
        ShoppingCartResponse shoppingCartResponse=shoppingCartService.save(shoppingCartRequest,userId);
        return new ResponseEntity<>(shoppingCartResponse, HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCart(@PathVariable Long id,@AuthenticationPrincipal UserPrinciple userPrinciple, @RequestBody ShoppingCartRequest shoppingCartRequest)throws CustomException {
        Long userId=userPrinciple.getUser().getId();

        ShoppingCartResponse shoppingCartResponse=shoppingCartService.updateQuantity(shoppingCartRequest,userId,id);
        return new ResponseEntity<>(shoppingCartResponse, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        shoppingCartService.delete(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);

    }
    @DeleteMapping
    public ResponseEntity<?> deleteAll(@AuthenticationPrincipal UserPrinciple userPrinciple) {
        Long userId=userPrinciple.getUser().getId();
        shoppingCartService.deleteAll(userId);
        return new ResponseEntity<>("Delete All Successfully", HttpStatus.OK);

    }
}

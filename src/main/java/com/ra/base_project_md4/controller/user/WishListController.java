package com.ra.base_project_md4.controller.user;

import com.ra.base_project_md4.model.entity.Product;
import com.ra.base_project_md4.security.UserPrinciple;
import com.ra.base_project_md4.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/wishlist")
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;
    @PostMapping("/add")
    public ResponseEntity<?> addToWishList(@AuthenticationPrincipal UserPrinciple userPrinciple, @RequestParam Long productId) {
        Long userId=userPrinciple.getUser().getId();
        String action =wishListService.addWishList(userId, productId);
        if ("added".equals(action)) {
            return new ResponseEntity<>("Product added to wishlist", HttpStatus.CREATED);
        } else if ("deleted".equals(action)) {
            return new ResponseEntity<>("Product removed from wishlist", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getWishList(@AuthenticationPrincipal UserPrinciple userPrinciple) {
        Long userId=userPrinciple.getUser().getId();
        List<Product> wishList = wishListService.getWishList(userId);
        return new ResponseEntity<>(wishList, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFromWishList(@AuthenticationPrincipal UserPrinciple userPrinciple, @RequestParam Long productId) {
        Long userId=userPrinciple.getUser().getId();
        wishListService.removeWishList(userId, productId);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }
}

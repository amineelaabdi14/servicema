package com.youcode.servicema.controllers;

import com.youcode.servicema.domain.entities.City;
import com.youcode.servicema.domain.entities.RefreshToken;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.dto.requests.BecomeASellerRequest;
import com.youcode.servicema.dto.requests.EditPofileRequest;
import com.youcode.servicema.dto.responses.AuthenticationResponse;
import com.youcode.servicema.dto.responses.BecomeASellerResponse;
import com.youcode.servicema.dto.responses.EditPofileResponse;
import com.youcode.servicema.services.RefreshTokenService;
import com.youcode.servicema.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PutMapping()
    public ResponseEntity<EditPofileResponse> updateUser(@RequestBody EditPofileRequest editProfileRequest) {
        Optional<User> user = userService.updateProfile(editProfileRequest);
        if (user.isPresent()) {
            ResponseEntity<EditPofileResponse> response = new ResponseEntity<>(EditPofileResponse.builder().
                    name(user.get().getName())
                    .city(City.builder().name(user.get().getCity().getName()).build())
                    .description(user.get().getDescription())
                    .phone(user.get().getPhone())
                    .build(), HttpStatus.OK);
            return response;
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('BECOME_SELLER')")
    @PostMapping("/becomeSellser")
    public ResponseEntity becomeSellser(@RequestBody BecomeASellerRequest becomeASellerRequest) {
        Optional<User> user = userService.becomeAsellser(becomeASellerRequest);
        if (user.isPresent()) {
            return ResponseEntity.ok(BecomeASellerResponse.builder()
                    .title(becomeASellerRequest.getTitle())
                    .description(becomeASellerRequest.getDescription())
                    .phone(becomeASellerRequest.getPhone())
                    .build());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/getByJwt")
    public ResponseEntity getUserByJwt() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(AuthenticationResponse.builder()
                .name(currentUser.getName())
                .city(City.builder().name(currentUser.getCity().getName()).build())
                .description(currentUser.getDescription())
                .phone(currentUser.getPhone())
                .email(currentUser.getEmail())
                .role(currentUser.getRole().getName())
                .build());
    }
}

package com.youcode.servicema.controllers;

import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.dto.requests.AuthenticationRequest;
import com.youcode.servicema.dto.requests.RegisterRequest;
import com.youcode.servicema.dto.responses.AuthenticationResponse;
import com.youcode.servicema.security.JwtService;
import com.youcode.servicema.services.AuthenticationService;
import com.youcode.servicema.services.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuhController {
    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResponse authenticate = authenticationService.authenticate(request);
        return ResponseEntity.ok(authenticate);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        AuthenticationResponse member = authenticationService.register(request);
        return ResponseEntity.ok(member);
    }

//    @PostMapping("/refresh")
//    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody @Valid RefreshRequest refreshToken) {
//        return ResponseEntity.ok(authenticationService.generateRefreshToken(refreshToken.getRefreshToken()));
//    }
}

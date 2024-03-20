package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.RefreshToken;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.dto.requests.AuthenticationRequest;
import com.youcode.servicema.dto.requests.RegisterRequest;
import com.youcode.servicema.dto.responses.AuthenticationResponse;
import com.youcode.servicema.exceptions.CustomException;
import com.youcode.servicema.repositories.UserRepository;
import com.youcode.servicema.security.JwtService;
import com.youcode.servicema.services.AuthenticationService;
import com.youcode.servicema.services.AuthorityService;
import com.youcode.servicema.services.RefreshTokenService;
import com.youcode.servicema.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new CustomException("Email already exists", HttpStatus.BAD_REQUEST);
        }
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleService.findDefaultRole().orElse(null))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getEmail());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken.getToken())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .city(user.getCity())
                .phone(user.getPhone())
                .description(user.getDescription())
                .build();
    }
//
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getEmail());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken.getToken())
                .name(user.getName())
                .description(user.getDescription())
                .phone(user.getPhone())
                .city(user.getCity())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }

//
//    @Override
//    public AuthenticationResponse generateRefreshToken(String refreshToken) {
//        RefreshToken foundRefreshToken = refreshTokenService.findByToken(refreshToken)
//                .orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
//        if (!foundRefreshToken.isValid()) {
//            throw new TokenRefreshException(refreshToken, "Refresh token is no longer valid");
//        }
//        User user = foundRefreshToken.getUser();
//        String accessToken = jwtUtils.generateTokenFromUsername(user.getEmail());
//        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user.getId());
//        return new AuthenticationResponse(accessToken, newRefreshToken.getToken(), MemberResponse.fromMember(user));
//    }
}
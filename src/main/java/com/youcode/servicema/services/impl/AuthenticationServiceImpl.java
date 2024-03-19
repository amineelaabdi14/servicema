package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.dto.request.AuthenticationRequest;
import com.youcode.servicema.dto.request.RegisterRequest;
import com.youcode.servicema.dto.response.AuthenticationResponse;
import com.youcode.servicema.repositories.UserRepository;
import com.youcode.servicema.security.JwtService;
import com.youcode.servicema.services.AuthenticationService;
import com.youcode.servicema.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder().name(request.getName()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(roleService.findDefaultRole().orElse(null)).build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).email(user.getEmail()).name(user.getName()).role(user.getRole()).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).email(user.getEmail()).name(user.getName()).role(user.getRole()).build();
    }
}
package com.youcode.servicema.services;

import com.youcode.servicema.dto.requests.AuthenticationRequest;
import com.youcode.servicema.dto.requests.RegisterRequest;
import com.youcode.servicema.dto.responses.AuthenticationResponse;
import org.springframework.stereotype.Component;


@Component
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest user);
    AuthenticationResponse authenticate(AuthenticationRequest user);
//    AuthenticationResponse generateRefreshToken(String refreshToken);


}


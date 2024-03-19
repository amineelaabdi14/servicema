package com.youcode.servicema.services;

import com.youcode.servicema.dto.request.AuthenticationRequest;
import com.youcode.servicema.dto.request.RegisterRequest;
import com.youcode.servicema.dto.response.AuthenticationResponse;
import org.springframework.stereotype.Component;


@Component
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest user);

    AuthenticationResponse authenticate(AuthenticationRequest user);

}


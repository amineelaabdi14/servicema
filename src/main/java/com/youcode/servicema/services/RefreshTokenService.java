package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.RefreshToken;
import com.youcode.servicema.dto.requests.RefreshRequest;
import com.youcode.servicema.dto.requests.RefreshTokenRequest;
import com.youcode.servicema.dto.responses.RefreshTokenResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RefreshTokenService {
     RefreshToken createRefreshToken(String email);

     RefreshToken verifyExpiration(RefreshToken token);

     Optional<RefreshToken> findByToken(String token);

     RefreshTokenResponse refreshToken(RefreshRequest refreshTokenRequestDTO);
}
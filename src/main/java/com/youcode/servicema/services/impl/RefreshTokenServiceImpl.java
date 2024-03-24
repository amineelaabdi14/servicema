package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.RefreshToken;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.dto.requests.RefreshRequest;
import com.youcode.servicema.dto.requests.RefreshTokenRequest;
import com.youcode.servicema.dto.responses.RefreshTokenResponse;
import com.youcode.servicema.exceptions.CustomException;
import com.youcode.servicema.repositories.RefreshTokenRepository;
import com.youcode.servicema.repositories.UserRepository;
import com.youcode.servicema.security.JwtService;
import com.youcode.servicema.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    private final JwtService jwtService;
    @Override
    public RefreshToken createRefreshToken(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        Optional<RefreshToken> existingTokenOpt = refreshTokenRepository.findByUserInfo(user);

        if (existingTokenOpt.isPresent()) {
            //? if exists update the token and expiry date
            RefreshToken existingToken = existingTokenOpt.get();
            existingToken.setToken(UUID.randomUUID().toString());
            existingToken.setExpiryDate(Instant.now().plus(Duration.ofDays(365)));
            return refreshTokenRepository.save(existingToken);
        } else {
            //? if no create a new one
            RefreshToken refreshToken = RefreshToken.builder()
                    .userInfo(user)
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plus(Duration.ofDays(365)))
                    .build();
            return refreshTokenRepository.save(refreshToken);
        }
    }
    @Override
    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new CustomException(token.getToken() + " Refresh token is expired. Please make a new login..!",HttpStatus.UNAUTHORIZED);
        }
        return token;
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshRequest refreshTokenRequestDTO) {
        RefreshToken refreshToken = findByToken(refreshTokenRequestDTO.getRefreshToken())
                .orElseThrow(() -> new CustomException("Invalid refresh Token", HttpStatus.NOT_FOUND));
        verifyExpiration(refreshToken);
        String token = jwtService.generateToken(refreshToken.getUserInfo());

        return RefreshTokenResponse.builder()
                .token(token)
                .refreshToken(refreshTokenRequestDTO.getRefreshToken())
                .build();

    }

}


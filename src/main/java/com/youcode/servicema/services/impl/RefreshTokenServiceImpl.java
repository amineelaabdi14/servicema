package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.RefreshToken;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.dto.requests.RefreshTokenRequest;
import com.youcode.servicema.dto.responses.RefreshTokenResponse;
import com.youcode.servicema.repositories.RefreshTokenRepository;
import com.youcode.servicema.repositories.UserRepository;
import com.youcode.servicema.security.JwtService;
import com.youcode.servicema.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
            existingToken.setExpiryDate(Instant.now().plusMillis(600000));
            return refreshTokenRepository.save(existingToken);
        } else {
            //? if no create a new one
            RefreshToken refreshToken = RefreshToken.builder()
                    .userInfo(user)
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis(600000))
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
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequestDTO) {
        RefreshToken refreshToken = findByToken(refreshTokenRequestDTO.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Invalid refresh Token"));
        verifyExpiration(refreshToken);
        String token = jwtService.generateToken(refreshToken.getUserInfo());


        return RefreshTokenResponse.builder()
                .token(token)
                .refreshToken(refreshTokenRequestDTO.getRefreshToken())
                .build();

    }

}


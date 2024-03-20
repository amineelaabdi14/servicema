package com.youcode.servicema.dto.responses;

import com.youcode.servicema.domain.entities.City;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{
    private String name;
    private String email;
    @Nullable
    private String phone;
    @Nullable
    private City city;
    @Nullable
    private String description;
    private String token;
    private String refreshToken;
    private String role;
}

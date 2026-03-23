package com.example.cafeteriamanagerment.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {
    private final String accessToken;
    private final String tokenType;

    private final String refreshToken;



}


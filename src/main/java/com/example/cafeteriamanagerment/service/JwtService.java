package com.example.cafeteriamanagerment.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final Key key;
    private final long accessTtlSeconds;
    private final long refreshTtlSeconds;

    public JwtService(
            @Value("${app.security.jwt.secret}") String secret,
            @Value("${app.security.jwt.access-token-ttl-seconds}") long accessTtlSeconds,
            @Value("${app.security.jwt.refresh-token-ttl-seconds}") long refreshTtlSeconds
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTtlSeconds = accessTtlSeconds;
        this.refreshTtlSeconds = refreshTtlSeconds;
    }

    public String generateAccessToken(Long userId, String phone, String role) {
        return buildToken(userId, phone, role, "access", accessTtlSeconds);
    }

    public String generateRefreshToken(Long userId, String phone, String role) {
        return buildToken(userId, phone, role, "refresh", refreshTtlSeconds);
    }

    private String buildToken(Long userId, String phone, String role, String type, long ttlSeconds) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(phone)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(ttlSeconds)))
                .claim("uid", userId)
                .claim("role", role)
                .claim("typ", type)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
    }

}

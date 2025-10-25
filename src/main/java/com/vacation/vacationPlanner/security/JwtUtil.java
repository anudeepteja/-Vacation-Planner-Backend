package com.vacation.vacationPlanner.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ Generate a secure key suitable for HS256
    private final Key jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long accessTokenExpiration = 1 * 60 * 1000; // 1 minutes
    private final long refreshTokenExpiration = 7 * 24 * 60 * 60 * 1000; // 7 days
    //private final long refreshTokenExpiration = 2 *  60 * 1000; // 2 min

    // Generate Access Token
    public String generateAccessToken(Integer userId, String username) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(jwtSecretKey) // Use Key object directly
                .compact();
    }

    // Generate Refresh Token
    public String generateRefreshToken(Integer userId, String username) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(jwtSecretKey)
                .compact();
    }

    // Parse token claims safely
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return getClaims(token).get("username", String.class);
    }

    public Integer extractUserId(String token) {
        return Integer.valueOf(getClaims(token).getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

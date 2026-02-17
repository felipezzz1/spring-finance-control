package com.fezor.spring_finance_control.security.service;

import com.fezor.spring_finance_control.model.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final Dotenv dotenv = Dotenv.load();

    private final String key = dotenv.get("SECRET_KEY");

    public String generateToken(User user) {

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(Duration.ofDays(1))))
                .signWith(getSignKey())
                .compact();

    }

    private Key getSignKey() {
        byte[] keyBytes = key.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    public boolean isValidToken(String token, String email) {
        final String e = extractEmail(token);
        return e.equals(email) && !isTokenExpired(token);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}

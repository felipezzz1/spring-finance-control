package com.fezor.spring_finance_control.service;

import com.fezor.spring_finance_control.model.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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
}

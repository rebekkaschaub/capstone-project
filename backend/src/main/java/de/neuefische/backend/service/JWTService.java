package de.neuefische.backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTService {

    @Value("${jwt.secret:}")
    private String secret;

    public String createToken(HashMap<String, Object> claims, String subject) {
    return Jwts.builder()
            .addClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plus(Duration.ofHours(1))))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }
}
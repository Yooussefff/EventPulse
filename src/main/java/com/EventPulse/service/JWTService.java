package com.EventPulse.service;

import com.EventPulse.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.nio.charset.StandardCharsets;

@Service
public class JWTService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.issuer}")
    private String issuer;

    public String generateToken(User user) {
        Key hmacKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(user.getEmail())
                .claim("username", user.getUserName())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(hmacKey, SignatureAlgorithm.HS256)
                .compact();
    }
}

package com.EventPulse.service;

import com.EventPulse.entities.User;
import io.jsonwebtoken.Claims;
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

    private Key getSigningKey() {
        return new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(User user) {
        Key key = getSigningKey();

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(user.getEmail())
                .claim("username", user.getUserName())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            Key key = getSigningKey();

            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {
            return null; // invalid token
        }
    }
}

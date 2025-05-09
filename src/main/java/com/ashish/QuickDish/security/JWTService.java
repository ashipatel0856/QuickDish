//package com.ashish.QuickDish.security;
//
//import com.ashish.QuickDish.Entity.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//
//@Service
//public class JWTService {
//
//    @Value("${jwt.SecretKey}")
//    private String jwtScretKey;
//
//    private SecretKey getSecretKey() {
//        return Keys.hmacShaKeyFor(jwtScretKey.getBytes(StandardCharsets.UTF_8));
//    }
//
//    public  String generateAccessToken(User user) {
//        return Jwts.builder()
//                .subject(user.getId().toString())
//                .claim("email", user.getEmail())
//                .claim("role", user.getRole())
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // for 10 minuts
//                .signWith(getSecretKey())
//                .compact();
//    }
//
//    public String generateRefreshToken(User user) {
//        return Jwts.builder()
//                .subject(user.getId().toString())
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24*30*6)) // for 6 month
//                .signWith(getSecretKey())
//                .compact();
//    }
//
//
//
//    public Long getUserIdFromToken(String refreshToken) {
//        Claims claims = Jwts.parser()
//                .verifyWith(getSecretKey())
//                .build()
//                .parseSignedClaims(refreshToken)
//                .getPayload();
//        return Long.valueOf(claims.getSubject());
//    }
//}

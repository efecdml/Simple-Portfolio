package com.gungorefe.simpleportfolio.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "e834e7f873fb3a211879d99aa0978f75ffcfc5dd644c74c25c1f2eb0897e823d0f6328b" +
            "f08b885687964f32454b93d5bf3b68606a82c8f2867bea88d2bb4f661";
    private static final long EXPIRATION_TIME = TimeUnit.DAYS.toMillis(30);

    public String extractUsername(String token) {
        return extractClaim(
                token,
                Claims::getSubject
        );
    }

    public String extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        String result = claims.get("roles", String.class);

        return result;
    }

    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {
        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(
                new HashMap<>(),
                userDetails
        );
    }

    public String generateToken(
            Map<String, String> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(
                        getSignInKey(),
                        SignatureAlgorithm.HS512
                )
                .compact();
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ) {
        String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(
                token,
                Claims::getExpiration
        );
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(bytes);
    }
}

package com.college.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Map;

@Service
public class JWTUtils {
    public static final String KEY="hdslakhfdddasoghsaioghasiopghasipoghsaipghasghisag";

    public String issueToken(String subject,
                             Map<String,Object> claims)
    {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(14, ChronoUnit.DAYS)))
                .signWith(getSignKey())
                .compact();

    }

    // Get the signing key for JWT token
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(KEY.getBytes());
    }

    private Claims getClaims(String token)
    {
        return Jwts.parser().verifyWith(getSignKey()).build()
                .parseSignedClaims(token).getPayload();
    }

    public String getSubject(String token)
    {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isTokenValid(String jwt,String username)
    {
        String subject = getSubject(jwt);
        return username.equals(subject) && !isValid(jwt);
    }

    private boolean isValid(String jwt) {
        return getClaims(jwt).getExpiration().before(Date.from(Instant.now()));
    }
}

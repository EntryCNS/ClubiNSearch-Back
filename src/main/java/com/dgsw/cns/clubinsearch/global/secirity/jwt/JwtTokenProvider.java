package com.dgsw.cns.clubinsearch.global.secirity.jwt;

import com.dgsw.cns.clubinsearch.domain.auth.domain.RefreshToken;
import com.dgsw.cns.clubinsearch.domain.token.presentation.dto.response.AccessTokenResponse;
import com.dgsw.cns.clubinsearch.global.properties.JwtProperties;
import com.dgsw.cns.clubinsearch.global.redis.RedisService;
import com.dgsw.cns.clubinsearch.global.secirity.jwt.enums.JwtType;
import com.dgsw.cns.clubinsearch.global.secirity.jwt.exception.ExpiredTokenException;
import com.dgsw.cns.clubinsearch.global.secirity.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final RedisService redisService;

    private final JwtProperties jwtProperties;


    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String email, JwtType type) {
        Claims claims = Jwts.claims();
        claims.put("email", email);

        Long exp = 0L;
        String secretKey = "";

        switch (type) {
            case ACCESS:
                claims.put("type", type);
                secretKey = jwtProperties.getAccessKey();
                exp = jwtProperties.getAccessExp();
                break;
            case REFRESH:
                claims.put("type", type);
                secretKey = jwtProperties.getRefreshKey();
                exp = jwtProperties.getRefreshExp();
                break;
        }

        Date now = new Date();
        Map<String, Object> header = new HashMap<>();
        header.put("type", "JWT");

        String token = Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + exp))
                .signWith(getSigningKey(secretKey), SignatureAlgorithm.HS256)
                .compact();

        if (type.equals(JwtType.REFRESH)) {
            redisService.save(
                    RefreshToken.builder()
                            .refreshToken(token)
                            .email(email)
                            .build()
            );
        }

        return token;
    }

    public AccessTokenResponse refreshAccessToken(String refreshToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey(jwtProperties.getRefreshKey()))
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        return new AccessTokenResponse(createToken(claims.get("email", String.class), JwtType.ACCESS));
    }

    public Claims validateToken(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey(jwtProperties.getAccessKey()))
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredTokenException e) {
            throw  ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.INVALID_TOKEN_EXCEPTION;
        }
    }
}

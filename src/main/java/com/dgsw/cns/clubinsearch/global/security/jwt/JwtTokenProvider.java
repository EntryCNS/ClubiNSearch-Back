package com.dgsw.cns.clubinsearch.global.security.jwt;

import com.dgsw.cns.clubinsearch.domain.auth.domain.RefreshToken;
import com.dgsw.cns.clubinsearch.domain.token.presentation.dto.response.AccessTokenResponse;
import com.dgsw.cns.clubinsearch.global.properties.JwtProperties;
import com.dgsw.cns.clubinsearch.global.redis.RedisService;
import com.dgsw.cns.clubinsearch.global.security.jwt.enums.JwtType;
import com.dgsw.cns.clubinsearch.global.security.jwt.exception.ExpiredTokenException;
import com.dgsw.cns.clubinsearch.global.security.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
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

    public String createToken(String accountId, JwtType type) {
        Claims claims = Jwts.claims();
        claims.put("accountId", accountId);

        String secretKey = "";

        Instant now = Instant.now();
        Duration exp = Duration.ZERO;

        switch (type) {
            case ACCESS:
                claims.put("type", type);
                secretKey = jwtProperties.getAccessKey();
                exp.plus(Duration.ofDays(1));
                break;
            case REFRESH:
                claims.put("type", type);
                secretKey = jwtProperties.getRefreshKey();
                exp.plus(Duration.ofDays(7));
                break;
        }

        Map<String, Object> header = new HashMap<>();
        header.put("type", "JWT");

        String token = Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .setExpiration(Date.from(now.plus(exp)))
                .signWith(getSigningKey(secretKey), SignatureAlgorithm.HS256)
                .compact();

        if (type.equals(JwtType.REFRESH)) {
            redisService.save(
                    RefreshToken.builder()
                            .refreshToken(token)
                            .accountId(accountId)
                            .build()
            );
        }

        return token;
    }

    public AccessTokenResponse refreshAccessToken(String refreshToken) {
        try {
            return new AccessTokenResponse(
                createToken(
                         Jwts.parserBuilder()
                        .setSigningKey(getSigningKey(jwtProperties.getRefreshKey()))
                        .build()
                        .parseClaimsJws(refreshToken)
                        .getBody()
                        .get("accountId" ,String.class)
                        ,JwtType.ACCESS
                )
            );
        } catch (ExpiredTokenException e) {
            throw  ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.INVALID_TOKEN_EXCEPTION;
        }
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

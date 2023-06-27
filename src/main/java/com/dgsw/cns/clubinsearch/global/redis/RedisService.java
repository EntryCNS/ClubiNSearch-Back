package com.dgsw.cns.clubinsearch.global.redis;

import com.dgsw.cns.clubinsearch.domain.auth.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate redisTemplate;

    public void save(RefreshToken refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getRefreshToken(), refreshToken.getAccountId());
        redisTemplate.expire(refreshToken.getRefreshToken(), 3600L, TimeUnit.SECONDS);
    }
}

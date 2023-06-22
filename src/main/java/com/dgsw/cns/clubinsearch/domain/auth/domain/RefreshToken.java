package com.dgsw.cns.clubinsearch.domain.auth.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 3600)
@RequiredArgsConstructor
@Getter
public class RefreshToken {
    @Id
    private String refreshToken;
    private String accountId;

    @Builder
    public RefreshToken(String refreshToken, String accountId) {
        this.refreshToken = refreshToken;
        this.accountId = accountId;
    }
}

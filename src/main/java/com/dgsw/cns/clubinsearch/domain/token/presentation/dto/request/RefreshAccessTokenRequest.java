package com.dgsw.cns.clubinsearch.domain.token.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshAccessTokenRequest {
    private String refreshToken;
}

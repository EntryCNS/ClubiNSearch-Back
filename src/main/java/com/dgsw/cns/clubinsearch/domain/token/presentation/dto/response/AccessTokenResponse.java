package com.dgsw.cns.clubinsearch.domain.token.presentation.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class AccessTokenResponse {
    private String accessToken;
}

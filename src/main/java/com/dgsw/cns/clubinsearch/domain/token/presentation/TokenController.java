package com.dgsw.cns.clubinsearch.domain.token.presentation;

import com.dgsw.cns.clubinsearch.domain.token.presentation.dto.request.RefreshAccessTokenRequest;
import com.dgsw.cns.clubinsearch.domain.token.presentation.dto.response.AccessTokenResponse;
import com.dgsw.cns.clubinsearch.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenController {
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public AccessTokenResponse refreshAccessToken(
            @RequestBody RefreshAccessTokenRequest request
            ) {
        return jwtTokenProvider.refreshAccessToken(request.getRefreshToken());
    }
}

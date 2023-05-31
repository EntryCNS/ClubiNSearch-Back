package com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginUserRequest {
    private String accountId;
    private String password;
}

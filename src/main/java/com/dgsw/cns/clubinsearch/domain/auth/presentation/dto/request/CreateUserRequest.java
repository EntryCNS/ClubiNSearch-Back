package com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUserRequest {
    private String accountId;

    private String name;

    private String email;

    private String password;
}

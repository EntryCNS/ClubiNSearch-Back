package com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUserRequest {
    @Size(min = 3)
    private String accountId;

    @Size(min = 1)
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^[a-zA-z0-9]$")
    private String password;
}

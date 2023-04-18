package com.dgsw.cns.clubinsearch.domain.auth.presentation;

import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.request.LoginUserRequest;
import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.response.LoginResponse;
import com.dgsw.cns.clubinsearch.domain.auth.service.AuthService;
import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(
            @RequestBody CreateUserRequest request
    ) {
        authService.createUser(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse loginUser (
        @RequestBody LoginUserRequest request
    ) {
        return authService.loginUser(request);
    }
}

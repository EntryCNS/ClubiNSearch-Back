package com.dgsw.cns.clubinsearch.domain.auth.presentation;

import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.request.LoginUserRequest;
import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.response.LoginResponse;
import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.request.CreateUserRequest;
import com.dgsw.cns.clubinsearch.domain.auth.service.CreateUserService;
import com.dgsw.cns.clubinsearch.domain.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CreateUserService createUserService;
    private final LoginService loginUserService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(
            @RequestBody CreateUserRequest request
    ) {
        createUserService.execute(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse loginUser (
        @RequestBody LoginUserRequest request
    ) {
        return loginUserService.execute(request);
    }
}

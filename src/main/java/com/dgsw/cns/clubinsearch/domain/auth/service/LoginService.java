package com.dgsw.cns.clubinsearch.domain.auth.service;

import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.request.LoginUserRequest;
import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.response.LoginResponse;
import com.dgsw.cns.clubinsearch.domain.user.domain.User;
import com.dgsw.cns.clubinsearch.domain.user.domain.repository.UserRepository;
import com.dgsw.cns.clubinsearch.domain.user.exception.NotFoundAccountIdException;
import com.dgsw.cns.clubinsearch.domain.user.exception.NotMatchesPasswordException;
import com.dgsw.cns.clubinsearch.global.security.jwt.JwtTokenProvider;
import com.dgsw.cns.clubinsearch.global.security.jwt.enums.JwtType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse execute(LoginUserRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> NotFoundAccountIdException.EXCEPTION);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw NotMatchesPasswordException.EXCEPTION;
        }

        return new LoginResponse(
                jwtTokenProvider.createToken(request.getAccountId(), JwtType.ACCESS),
                jwtTokenProvider.createToken(request.getAccountId(), JwtType.REFRESH)
        );
    }


}

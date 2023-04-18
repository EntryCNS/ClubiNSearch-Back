package com.dgsw.cns.clubinsearch.domain.auth.service;

import com.dgsw.cns.clubinsearch.domain.user.exception.ExistsUserEmailException;
import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.request.CreateUserRequest;
import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.request.LoginUserRequest;
import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.response.LoginResponse;
import com.dgsw.cns.clubinsearch.domain.user.domain.User;
import com.dgsw.cns.clubinsearch.domain.user.domain.repository.UserRepository;
import com.dgsw.cns.clubinsearch.domain.user.exception.NotFoundUserEmailException;
import com.dgsw.cns.clubinsearch.domain.user.exception.PasswordNotMatchesException;
import com.dgsw.cns.clubinsearch.global.secirity.jwt.JwtTokenProvider;
import com.dgsw.cns.clubinsearch.global.secirity.jwt.enums.JwtType;
import com.dgsw.cns.clubinsearch.global.secirity.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final SecurityConfig securityConfig;

    private final JwtTokenProvider jwtTokenProvider;

    public void createUser(CreateUserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw ExistsUserEmailException.EXCEPTION;
        }

        userRepository.save(
                User.builder()
                        .accountId(request.getAccountId())
                        .email(request.getEmail())
                        .password(securityConfig.passwordEncoder().encode(request.getPassword()))
                        .build()
        );
    }

    public LoginResponse loginUser(LoginUserRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> NotFoundUserEmailException.EXCEPTION);

        if(!securityConfig.passwordEncoder().matches(request.getPassword(), user.getPassword())) {
            throw PasswordNotMatchesException.EXCEPTION;
        }
        String email = user.getEmail();
        return new LoginResponse(
                jwtTokenProvider.createToken(email, JwtType.ACCESS),
                jwtTokenProvider.createToken(email, JwtType.REFRESH)
        );
    }

}

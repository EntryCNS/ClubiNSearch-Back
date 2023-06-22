package com.dgsw.cns.clubinsearch.domain.auth.service;

import com.dgsw.cns.clubinsearch.domain.auth.presentation.dto.request.CreateUserRequest;
import com.dgsw.cns.clubinsearch.domain.user.domain.User;
import com.dgsw.cns.clubinsearch.domain.user.domain.enums.Role;
import com.dgsw.cns.clubinsearch.domain.user.domain.repository.UserRepository;
import com.dgsw.cns.clubinsearch.domain.user.exception.ExistsUserEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void execute(CreateUserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw ExistsUserEmailException.EXCEPTION;
        }

        userRepository.save(
                User.builder()
                        .accountId(request.getAccountId())
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.ROLE_ADMIN)
                        .build()
        );
    }

}

package com.dgsw.cns.clubinsearch.global.security.principle;

import com.dgsw.cns.clubinsearch.domain.user.domain.repository.UserRepository;
import com.dgsw.cns.clubinsearch.domain.user.exception.NotFoundAccountIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        return userRepository.findByAccountId(accountId)
                .map(AuthDetails::new)
                .orElseThrow(() -> NotFoundAccountIdException.EXCEPTION);
    }
}

package com.dgsw.cns.clubinsearch.global.filter.config;

import com.dgsw.cns.clubinsearch.global.filter.ExceptionFilter;
import com.dgsw.cns.clubinsearch.global.filter.JwtFilter;
import com.dgsw.cns.clubinsearch.global.secirity.jwt.JwtTokenProvider;
import com.dgsw.cns.clubinsearch.global.secirity.principle.AuthDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    private final AuthDetailsService authDetailsService;
    @Override
    public void configure(HttpSecurity builder){
        builder.addFilterBefore(new JwtFilter(jwtTokenProvider, authDetailsService), UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(new ExceptionFilter(objectMapper), JwtFilter.class);
    }
}

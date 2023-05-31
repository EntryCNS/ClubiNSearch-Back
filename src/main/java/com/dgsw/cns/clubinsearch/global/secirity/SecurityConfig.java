package com.dgsw.cns.clubinsearch.global.secirity;

import com.dgsw.cns.clubinsearch.domain.user.domain.enums.Role;
import com.dgsw.cns.clubinsearch.global.filter.config.FilterConfig;
import com.dgsw.cns.clubinsearch.global.secirity.principle.AuthDetailsService;
import com.dgsw.cns.clubinsearch.global.secirity.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthDetailsService authDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper mapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
                web.ignoring()
                        .mvcMatchers("/swagger-ui/**",
                                "/swagger-resources/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html");
    }

    @Bean
    public SecurityFilterChain configure (HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()

                .antMatchers("/api/auth/**").permitAll()

                .antMatchers("/api/recruitment/").permitAll()
                .antMatchers("/api/recruitment/**").permitAll()
                .antMatchers("/api/recruitment/{id}").permitAll()

                .antMatchers("/api/resume/submit").permitAll()
                .antMatchers("/api/resume/admin/**").hasRole(Role.ROLE_ADMIN.getRole())

                .antMatchers("/api/token/refresh").permitAll()

                .anyRequest().authenticated()

                .and()
                .apply(new FilterConfig(jwtTokenProvider, mapper, authDetailsService));

        return http.build();
    }
}
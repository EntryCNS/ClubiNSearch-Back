package com.dgsw.cns.clubinsearch.domain.user.domain;

import com.dgsw.cns.clubinsearch.domain.user.domain.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountId;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Long id, String accountId, String name, String email, String password, Role role) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

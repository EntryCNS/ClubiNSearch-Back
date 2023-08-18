package com.dgsw.cns.clubinsearch.domain.user.domain;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.user.domain.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@Builder
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountId;

    private String name;

    private String email;

    private String password;

    @OneToOne
    private Club club;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return false;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(getId(), user.getId());
    }
}

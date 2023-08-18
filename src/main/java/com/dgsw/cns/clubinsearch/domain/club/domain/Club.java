package com.dgsw.cns.clubinsearch.domain.club.domain;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String profile;

    @Builder.Default
    @OneToMany(mappedBy = "club", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Recruitment> recruitments = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return false;
        if (obj == null || getClass() != obj.getClass()) return false;
        Club club = (Club) obj;
        return Objects.equals(getId(), club.getId());
    }

    public void addRecruitment(Recruitment recruitment) {
        recruitment.setClub(this);
        recruitments.add(recruitment);
    }

    public void updateProfile(String profileUrl) {
        this.profile = profileUrl;
    }
}

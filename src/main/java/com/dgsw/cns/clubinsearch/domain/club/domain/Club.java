package com.dgsw.cns.clubinsearch.domain.club.domain;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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

    @Builder.Default
    @OneToMany(mappedBy = "club", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Recruitment> recruitments = new HashSet<>();

    public void addRecruitment(Recruitment recruitment) {
        recruitment.setClub(this);
        recruitments.add(recruitment);
    }
}

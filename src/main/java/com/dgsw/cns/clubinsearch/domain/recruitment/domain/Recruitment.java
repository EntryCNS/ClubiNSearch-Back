package com.dgsw.cns.clubinsearch.domain.recruitment.domain;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.resume.domain.Resume;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String position;

    private String employmentType;

    private String detailContent;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Resume> resumes = new HashSet<>();

    public void addResume(Resume resume) {
        resume.setRecruitment(this);
        resumes.add(resume);
    }

}

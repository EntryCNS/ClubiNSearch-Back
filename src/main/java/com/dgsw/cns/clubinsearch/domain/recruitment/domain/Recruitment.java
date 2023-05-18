package com.dgsw.cns.clubinsearch.domain.recruitment.domain;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.EmploymentType;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.State;
import com.dgsw.cns.clubinsearch.domain.resume.domain.Resume;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
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

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    private String detailContent;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private State state;

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

    public void updateRecruitment(String title, String position, EmploymentType employmentType, String detailContent, LocalDate startDate, LocalDate endDate, State state) {
        this.title = title;
        this.position = position;
        this.employmentType = employmentType;
        this.detailContent = detailContent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
    }
}

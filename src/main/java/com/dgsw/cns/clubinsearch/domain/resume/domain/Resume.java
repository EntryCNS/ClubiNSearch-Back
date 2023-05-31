package com.dgsw.cns.clubinsearch.domain.resume.domain;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.resume.domain.enums.State;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String studentNo;

    private String contact;

    private String introduction;

    private String link;

    private String fileUrl;

    @Enumerated(EnumType.STRING)
    private State state;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    public void updateResumeState(State state) {
        this.state = state;
    }
}

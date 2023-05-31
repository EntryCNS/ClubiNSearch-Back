package com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.response;

import com.dgsw.cns.clubinsearch.domain.resume.domain.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResumeResponse {
    private Long id;

    private String name;

    private String studentNo;

    private String contact;

    private String introduction;

    private String link;

    private String fileUrl;

    private State state;

}

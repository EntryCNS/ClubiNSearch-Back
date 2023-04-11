package com.dgsw.cns.clubinsearch.domain.resume.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubmitResumeRequest {
    private Long recruitmentId;

    private String name;

    private String studentNo;

    private String contact;

    private String introduction;

    private String link;

}

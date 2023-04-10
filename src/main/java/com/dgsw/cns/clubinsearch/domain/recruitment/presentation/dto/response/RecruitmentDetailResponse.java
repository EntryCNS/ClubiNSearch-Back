package com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class RecruitmentDetailResponse {

    private String title;

    private String club;

    private String position;

    private String hiringType;

    private String detailContent;

}

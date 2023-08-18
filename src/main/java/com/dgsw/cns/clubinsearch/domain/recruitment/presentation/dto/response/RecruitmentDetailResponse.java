package com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.EmploymentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class RecruitmentDetailResponse {

    private String title;

    private Long clubId;

    private String clubName;

    private String position;

    private String detailContent;

    private String employmentType;

    private String startDate;

    private String endDate;

}

package com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.EmploymentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class RecruitmentResponse {

    private Long id;

    private String title;

    private String clubName;

    private String position;

    private EmploymentType employmentType;

}

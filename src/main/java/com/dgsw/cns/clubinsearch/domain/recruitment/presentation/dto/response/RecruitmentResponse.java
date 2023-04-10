package com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class RecruitmentResponse {

    private String title;

    private String clubName;

    private String position;

    private String hiringType;

}

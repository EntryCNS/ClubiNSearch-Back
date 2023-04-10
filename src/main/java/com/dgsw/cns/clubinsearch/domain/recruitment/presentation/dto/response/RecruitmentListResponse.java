package com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecruitmentListResponse {

    private List<RecruitmentResponse> recruitmentList;

}

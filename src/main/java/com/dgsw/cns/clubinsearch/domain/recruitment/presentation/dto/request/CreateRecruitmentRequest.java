package com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.request;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.EmploymentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRecruitmentRequest {

    @NotBlank(message = "동아리 이름이 필요합니다")
    private String clubName;

    @NotBlank(message = "채용 공고 이름이 필요합니다")
    private String title;

    @NotBlank(message = "채용 포지션이 필요합니다")
    private String position;

    private EmploymentType employmentType;

    @NotBlank(message = "채용에 관한 자세한 내용이 필요합니다")
    private String detailContent;

}

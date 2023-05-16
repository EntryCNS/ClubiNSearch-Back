package com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.request;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.EmploymentType;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull
    private Boolean isOpen;

    public Recruitment toEntity() {
        return Recruitment.builder()
                .title(title)
                .position(position)
                .employmentType(employmentType)
                .detailContent(detailContent)
                .startDate(startDate)
                .endDate(endDate)
                .state(isOpen ? State.OPEN : State.CLOSE)
                .build();
    }
}

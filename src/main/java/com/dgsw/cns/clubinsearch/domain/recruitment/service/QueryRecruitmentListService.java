package com.dgsw.cns.clubinsearch.domain.recruitment.service;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.EmploymentType;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.RecruitmentRepository;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response.RecruitmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryRecruitmentListService {

    private final RecruitmentRepository recruitmentRepository;

    @Transactional(readOnly = true)
    public List<RecruitmentResponse> execute(
            String clubName,
            String search,
            String position,
            EmploymentType employmentType) {
        List<Recruitment> recruitments = recruitmentRepository.searchRecruitment(
                clubName, search, position, employmentType
        );

        if(recruitments.isEmpty()) {
            return Collections.emptyList();
        }

        return recruitments.stream().map(
                it -> new RecruitmentResponse(it.getId(), it.getTitle(), it.getClub().getName(), it.getPosition(), it.getEmploymentType().getValue())
        ).collect(Collectors.toList());
    }

}

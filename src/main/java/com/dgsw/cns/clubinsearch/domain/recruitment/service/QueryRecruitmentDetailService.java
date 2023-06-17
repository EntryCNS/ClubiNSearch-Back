package com.dgsw.cns.clubinsearch.domain.recruitment.service;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.RecruitmentRepository;
import com.dgsw.cns.clubinsearch.domain.recruitment.exception.RecruitmentNotFoundException;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response.RecruitmentDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryRecruitmentDetailService {

    private final RecruitmentRepository recruitmentRepository;


    @Transactional(readOnly = true)
    public RecruitmentDetailResponse execute(
            Long id
    ) {
        Recruitment recruitment = recruitmentRepository.findById(id)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return new RecruitmentDetailResponse(
                recruitment.getTitle(), recruitment.getClub().getName(), recruitment.getPosition(), recruitment.getDetailContent(), recruitment.getEmploymentType()
        );
    }

}

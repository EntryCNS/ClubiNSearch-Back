package com.dgsw.cns.clubinsearch.domain.recruitment.service;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.State;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.RecruitmentRepository;
import com.dgsw.cns.clubinsearch.domain.recruitment.exception.RecruitmentNotFoundException;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.request.RecruitmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateRecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public void execute(Long id, RecruitmentRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(id)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        recruitment.updateRecruitment(
                request.getTitle(),
                request.getPosition(),
                request.getEmploymentType(),
                request.getDetailContent(),
                request.getStartDate(),
                request.getEndDate(),
                request.getIsOpen() ? State.OPEN : State.CLOSE
        );
    }
}

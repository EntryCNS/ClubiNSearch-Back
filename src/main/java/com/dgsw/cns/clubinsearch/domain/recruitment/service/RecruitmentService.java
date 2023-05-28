package com.dgsw.cns.clubinsearch.domain.recruitment.service;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.club.domain.repository.ClubRepository;
import com.dgsw.cns.clubinsearch.domain.club.exception.ClubNotFoundException;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.EmploymentType;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.State;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.RecruitmentRepository;
import com.dgsw.cns.clubinsearch.domain.recruitment.exception.RecruitmentNotFoundException;
import com.dgsw.cns.clubinsearch.domain.recruitment.exception.RecruitmentsEmptyException;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.request.RecruitmentRequest;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response.RecruitmentDetailResponse;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response.RecruitmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    private final ClubRepository clubRepository;

    @Transactional
    public void createRecruitment(RecruitmentRequest request) {
        Recruitment recruitment = request.toEntity();

        Club club = clubRepository.findByName(request.getClubName())
                .orElseThrow(() -> ClubNotFoundException.EXCEPTION);

        club.addRecruitment(recruitment);

        recruitmentRepository.save(recruitment);
    }

    @Transactional
    public void updateRecruitment(Long id, RecruitmentRequest request) {
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

    @Transactional(readOnly = true)
    public List<RecruitmentResponse> getRecruitmentList(
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
                it -> new RecruitmentResponse(it.getId(), it.getTitle(), it.getClub().getName(), it.getPosition(), it.getEmploymentType())
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RecruitmentDetailResponse getRecruitmentById(
            Long id
    ) {
        Recruitment recruitment = recruitmentRepository.findById(id)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return new RecruitmentDetailResponse(
                recruitment.getTitle(), recruitment.getClub().getName(), recruitment.getPosition(), recruitment.getDetailContent(), recruitment.getEmploymentType()
        );
    }
}
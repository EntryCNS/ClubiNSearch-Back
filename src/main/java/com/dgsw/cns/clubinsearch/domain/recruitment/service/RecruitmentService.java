package com.dgsw.cns.clubinsearch.domain.recruitment.service;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.club.domain.repository.ClubRepository;
import com.dgsw.cns.clubinsearch.domain.club.exception.ClubNotFoundException;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.RecruitmentRepository;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.request.CreateRecruitmentRequest;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response.RecruitmentListResponse;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response.RecruitmentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    private final ClubRepository clubRepository;

    public void createRecruitment(CreateRecruitmentRequest request) {

        Club club = clubRepository.findByName(request.getClubName())
                .orElseThrow(() -> ClubNotFoundException.EXCEPTION);

        Recruitment recruitment = Recruitment.builder()
                .title(request.getTitle())
                .position(request.getPosition())
                .employmentType(request.getEmploymentType())
                .detailContent(request.getDetailContent())
                .build();

        club.addRecruitment(recruitment);

        recruitmentRepository.save(recruitment);
    }

    @Transactional(readOnly = true)
    public List<RecruitmentResponse> getRecruitmentListByClubName(
            String clubName,
            String search,
            String position,
            String employmentType) {

        if (clubName.isEmpty() && search.isEmpty() && position.isEmpty() && employmentType.isEmpty()) {
            return getRecruitmentList();
        }

        Club club = clubRepository.findAllByNameContaining(clubName)
                .orElseThrow(() -> ClubNotFoundException.EXCEPTION);

        List<Recruitment> recruitments = recruitmentRepository.findByClub_IdAndTitleAndPositionAndEmploymentType(
                club.getId(), search, position, employmentType
        );
        return recruitments.stream().map(
                it -> new RecruitmentResponse(it.getTitle(), it.getClub().getName(), it.getPosition(), it.getEmploymentType())
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecruitmentResponse> getRecruitmentList() {
        List<Recruitment> recruitment = recruitmentRepository.findAll();

        return recruitment.stream().map(
                it -> new RecruitmentResponse(it.getTitle(), it.getClub().getName(), it.getPosition(), it.getEmploymentType())
        ).collect(Collectors.toList());
    }
}
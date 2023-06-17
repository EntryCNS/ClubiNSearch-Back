package com.dgsw.cns.clubinsearch.domain.recruitment.service;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.club.domain.repository.ClubRepository;
import com.dgsw.cns.clubinsearch.domain.club.exception.ClubNotFoundException;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.RecruitmentRepository;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.request.RecruitmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateRecruitmentService {

    private final ClubRepository clubRepository;

    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public void execute(RecruitmentRequest request) {
        Recruitment recruitment = request.toEntity();

        Club club = clubRepository.findByName(request.getClubName())
                .orElseThrow(() -> ClubNotFoundException.EXCEPTION);

        club.addRecruitment(recruitment);

        recruitmentRepository.save(recruitment);
    }
}

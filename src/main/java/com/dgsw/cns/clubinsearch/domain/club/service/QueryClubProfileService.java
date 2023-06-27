package com.dgsw.cns.clubinsearch.domain.club.service;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.club.domain.repository.ClubRepository;
import com.dgsw.cns.clubinsearch.domain.club.exception.ClubNotFoundException;
import com.dgsw.cns.clubinsearch.domain.club.presentation.dto.response.ClubProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryClubProfileService {

    private final ClubRepository clubRepository;

    @Transactional(readOnly = true)
    public ClubProfileResponse execute(Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> ClubNotFoundException.EXCEPTION);

        return new ClubProfileResponse(
                club.getProfile()
        );
    }

}

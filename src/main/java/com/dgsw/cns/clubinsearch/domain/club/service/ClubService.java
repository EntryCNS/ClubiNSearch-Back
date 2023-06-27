package com.dgsw.cns.clubinsearch.domain.club.service;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.club.domain.repository.ClubRepository;
import com.dgsw.cns.clubinsearch.domain.club.exception.ClubNameExistsException;
import com.dgsw.cns.clubinsearch.domain.club.presentation.dto.request.RegisterClubRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public void registerClub(RegisterClubRequest request) {

        if(clubRepository.existsByName(request.getClubName())) {
            throw ClubNameExistsException.EXCEPTION;
        }

        Club club = Club.builder()
                .name(request.getClubName())
                .build();

        clubRepository.save(club);

    }

}

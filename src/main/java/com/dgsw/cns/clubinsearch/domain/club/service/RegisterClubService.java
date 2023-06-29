package com.dgsw.cns.clubinsearch.domain.club.service;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.club.domain.repository.ClubRepository;
import com.dgsw.cns.clubinsearch.domain.club.exception.ClubNameExistsException;
import com.dgsw.cns.clubinsearch.domain.club.presentation.dto.request.RegisterClubRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterClubService {

    private final ClubRepository clubRepository;

    @Value("${app.club.profile}")
    private String basicProfile;

    @Transactional
    public void execute(RegisterClubRequest request) {

        if(clubRepository.existsByName(request.getClubName())) {
            throw ClubNameExistsException.EXCEPTION;
        }

        Club club = Club.builder()
                .name(request.getClubName())
                .profile(basicProfile)
                .build();

        clubRepository.save(club);
    }

}

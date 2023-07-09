package com.dgsw.cns.clubinsearch.domain.club.service;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.club.domain.repository.ClubRepository;
import com.dgsw.cns.clubinsearch.domain.club.exception.ClubNotFoundException;
import com.dgsw.cns.clubinsearch.domain.club.presentation.dto.request.AddClubProfileRequest;
import com.dgsw.cns.clubinsearch.thirdparty.s3.enums.Dir;
import com.dgsw.cns.clubinsearch.thirdparty.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddClubProfileService {

    private final ClubRepository clubRepository;

    private final S3Service s3Service;

    @Transactional
    public void execute(
            AddClubProfileRequest request
    ) {
        Club club = clubRepository.findById(request.getClubId())
                .orElseThrow(() -> ClubNotFoundException.EXCEPTION);

        String profileUrl = s3Service.uploadFile(Dir.CLUB_PROFILE, request.getProfile());

        //TODO 변경한 프로필 s3에서 삭제시키기.

        club.updateProfile(profileUrl);
    }
}

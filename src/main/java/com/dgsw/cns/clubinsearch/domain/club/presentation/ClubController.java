package com.dgsw.cns.clubinsearch.domain.club.presentation;

import com.dgsw.cns.clubinsearch.domain.club.presentation.dto.request.AddClubProfileRequest;
import com.dgsw.cns.clubinsearch.domain.club.presentation.dto.request.RegisterClubRequest;
import com.dgsw.cns.clubinsearch.domain.club.presentation.dto.response.ClubProfileResponse;
import com.dgsw.cns.clubinsearch.domain.club.service.AddClubProfileService;
import com.dgsw.cns.clubinsearch.domain.club.service.ClubService;
import com.dgsw.cns.clubinsearch.domain.club.service.QueryClubProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/club")
public class ClubController {

    private final ClubService clubService;

    private final AddClubProfileService addClubProfileService;

    private final QueryClubProfileService queryClubProfileService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerClub(
            @RequestBody @Valid RegisterClubRequest request
            ) {
        clubService.registerClub(request);
    }

    @PostMapping("/profile")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProfile(
            @ModelAttribute AddClubProfileRequest request
            ) {
        addClubProfileService.execute(request);
    }

    @GetMapping("/profile/{clubName}")
    @ResponseStatus(HttpStatus.OK)
    public ClubProfileResponse getProfile(
            @PathVariable("clubName") String clubName
            ) {
        return queryClubProfileService.execute(clubName);
    }

}

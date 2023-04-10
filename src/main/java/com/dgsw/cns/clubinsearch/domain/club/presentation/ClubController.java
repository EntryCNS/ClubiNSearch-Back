package com.dgsw.cns.clubinsearch.domain.club.presentation;

import com.dgsw.cns.clubinsearch.domain.club.presentation.dto.RegisterClubRequest;
import com.dgsw.cns.clubinsearch.domain.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/club")
public class ClubController {

    private final ClubService clubService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerClub(
            @RequestBody @Valid RegisterClubRequest request
            ) {
        clubService.registerClub(request);
    }

}

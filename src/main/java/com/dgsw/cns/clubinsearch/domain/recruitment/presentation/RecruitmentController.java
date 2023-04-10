package com.dgsw.cns.clubinsearch.domain.recruitment.presentation;

import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.request.CreateRecruitmentRequest;
import com.dgsw.cns.clubinsearch.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/recruitment")
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void crateRecruitment(
            @RequestBody @Valid CreateRecruitmentRequest request
            ) {
        recruitmentService.createRecruitment(request);
    }


}

package com.dgsw.cns.clubinsearch.domain.resume.presentation;

import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.request.SubmitResumeRequest;
import com.dgsw.cns.clubinsearch.domain.resume.service.SumbitResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final SumbitResumeService sumbitResumeService;

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public void submitResume(
            @ModelAttribute SubmitResumeRequest request) {
        sumbitResumeService.execute(request);
    }

}

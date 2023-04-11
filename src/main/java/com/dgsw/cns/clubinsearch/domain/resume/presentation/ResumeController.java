package com.dgsw.cns.clubinsearch.domain.resume.presentation;

import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.SubmitResumeRequest;
import com.dgsw.cns.clubinsearch.domain.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public void submitResume(
            @RequestBody SubmitResumeRequest request) {
        resumeService.submitResume(request);
    }
}

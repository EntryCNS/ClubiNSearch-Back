package com.dgsw.cns.clubinsearch.domain.resume.presentation;

import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.request.SubmitResumeRequest;
import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.response.ResumeResponse;
import com.dgsw.cns.clubinsearch.domain.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public void submitResume(
            @ModelAttribute SubmitResumeRequest request) {
        resumeService.  submitResume(request);
    }

    @GetMapping("/list/{recruitmentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResumeResponse> getResumeListByRecruitmentId(
            @PathVariable("recruitmentId") Long recruitmentId
    ) {
        return resumeService.getResumeListByRecruitmentId(recruitmentId);
    }
}

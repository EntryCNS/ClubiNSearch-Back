package com.dgsw.cns.clubinsearch.domain.resume.presentation;

import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.request.UpdateResumeStateRequest;
import com.dgsw.cns.clubinsearch.domain.resume.service.UpdateResumeStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/resume/admin")
@RequiredArgsConstructor
public class AdminResumeController {
    private final UpdateResumeStateService updateResumeStateService;

    @PutMapping("/state")
    @ResponseStatus(HttpStatus.OK)
    public void updateResumeState(@RequestBody UpdateResumeStateRequest request) {
        updateResumeStateService.execute(request);
    }
}

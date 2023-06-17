package com.dgsw.cns.clubinsearch.domain.resume.presentation;

import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.request.UpdateResumeStateRequest;
import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.response.ResumeResponse;
import com.dgsw.cns.clubinsearch.domain.resume.service.QueryResumeListService;
import com.dgsw.cns.clubinsearch.domain.resume.service.UpdateResumeStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/resume/admin")
@RequiredArgsConstructor
public class AdminResumeController {
    private final UpdateResumeStateService updateResumeStateService;

    private final QueryResumeListService queryResumeListService;

    @GetMapping("/list/{recruitmentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResumeResponse> getResumeListByRecruitmentId(
            @PathVariable("recruitmentId") Long recruitmentId
    ) {
        return queryResumeListService.execute(recruitmentId);
    }

    @PostMapping("/state")
    @ResponseStatus(HttpStatus.OK)
    public void updateResumeState(@RequestBody UpdateResumeStateRequest request) {
        updateResumeStateService.execute(request);
    }


}

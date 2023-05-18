package com.dgsw.cns.clubinsearch.domain.recruitment.presentation;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums.EmploymentType;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.request.RecruitmentRequest;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response.RecruitmentDetailResponse;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response.RecruitmentResponse;
import com.dgsw.cns.clubinsearch.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recruitment")
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void crateRecruitment(
            @RequestBody @Valid RecruitmentRequest request
            ) {
        recruitmentService.createRecruitment(request);
    }


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<RecruitmentResponse> getRecruitmentList(
            @RequestParam(value = "clubName", defaultValue = "") String clubName,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "position", defaultValue = "") String position,
            @RequestParam(value = "employmentType", defaultValue = "") EmploymentType employmentType
    ) {
        return recruitmentService.getRecruitmentList(clubName, search, position, employmentType);
    }

    @GetMapping("/{id}")
    public RecruitmentDetailResponse getRecruitmentById(
            @PathVariable Long id
    ) {
        return recruitmentService.getRecruitmentById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRecruitment(
            @PathVariable Long id,
            @RequestBody @Valid RecruitmentRequest request
    ) {
        recruitmentService.updateRecruitment(id, request);
    }
}

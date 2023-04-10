package com.dgsw.cns.clubinsearch.domain.recruitment.presentation;

import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.request.CreateRecruitmentRequest;
import com.dgsw.cns.clubinsearch.domain.recruitment.presentation.dto.response.RecruitmentResponse;
import com.dgsw.cns.clubinsearch.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<RecruitmentResponse> getRecruitmentListByClubName(
            @RequestParam(value = "clubName", defaultValue = "") String clubName,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "position", defaultValue = "") String position,
            @RequestParam(value = "employmentType", defaultValue = "") String employmentType
    ) {
        return recruitmentService.getRecruitmentList(clubName, search, position, employmentType);
    }
}

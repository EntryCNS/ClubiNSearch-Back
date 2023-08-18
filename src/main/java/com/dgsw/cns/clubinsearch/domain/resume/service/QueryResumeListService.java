package com.dgsw.cns.clubinsearch.domain.resume.service;

import com.dgsw.cns.clubinsearch.domain.club.domain.Club;
import com.dgsw.cns.clubinsearch.domain.club.domain.repository.ClubRepository;
import com.dgsw.cns.clubinsearch.domain.club.exception.ClubNotFoundException;
import com.dgsw.cns.clubinsearch.domain.resume.domain.Resume;
import com.dgsw.cns.clubinsearch.domain.resume.domain.enums.State;
import com.dgsw.cns.clubinsearch.domain.resume.domain.repository.ResumeRepository;
import com.dgsw.cns.clubinsearch.domain.resume.exception.ResumeListEmptyException;
import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.response.ResumeResponse;
import com.dgsw.cns.clubinsearch.domain.user.domain.User;
import com.dgsw.cns.clubinsearch.domain.user.facade.UserFacade;
import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryResumeListService {

    private final ResumeRepository resumeRepository;

    @Transactional(readOnly = true)
    public List<ResumeResponse> execute(
            Long recruitmentId
    ) {
        List<Resume> resumeList = resumeRepository.findAllByRecruitment_Id(recruitmentId);

        List<ResumeResponse> resumeResponseList = new ArrayList<>();

        resumeResponseList.add(createResumeResponse(State.SUBMIT, resumeList));
        resumeResponseList.add(createResumeResponse(State.INTERVIEW, resumeList));
        resumeResponseList.add(createResumeResponse(State.FINAL, resumeList));

        return resumeResponseList;
    }

    private ResumeResponse createResumeResponse(State state, List<Resume> resumeList) {
        List<ResumeResponse.MemberResponse> memberResponseList = resumeList.stream()
                .filter(resume -> resume.getState().equals(state))
                .map(resume -> new ResumeResponse.MemberResponse(
                        resume.getId(),
                        resume.getName(),
                        resume.getStudentNo(),
                        resume.getContact(),
                        resume.getIntroduction(),
                        resume.getLink(),
                        resume.getFileUrl()
                ))
                .collect(Collectors.toList());

        return new ResumeResponse(state, memberResponseList);
    }
}

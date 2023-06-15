package com.dgsw.cns.clubinsearch.domain.resume.service;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.RecruitmentRepository;
import com.dgsw.cns.clubinsearch.domain.recruitment.exception.RecruitmentNotFoundException;
import com.dgsw.cns.clubinsearch.domain.resume.domain.Resume;
import com.dgsw.cns.clubinsearch.domain.resume.domain.enums.State;
import com.dgsw.cns.clubinsearch.domain.resume.domain.repository.ResumeRepository;
import com.dgsw.cns.clubinsearch.domain.resume.exception.ResumeListEmptyException;
import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.request.SubmitResumeRequest;
import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.response.ResumeResponse;
import com.dgsw.cns.clubinsearch.thirdparty.s3.enums.Dir;
import com.dgsw.cns.clubinsearch.thirdparty.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    private final RecruitmentRepository recruitmentRepository;

    private final S3Service s3Service;
    public void submitResume(SubmitResumeRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(request.getRecruitmentId())
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        Resume resume = Resume.builder()
                .name(request.getName())
                .studentNo(request.getStudentNo())
                .contact(request.getContact())
                .introduction(request.getIntroduction())
                .link(request.getLink())
                .fileUrl(s3Service.uploadFile(Dir.RESUME, request.getFile()))
                .state(State.SUBMIT)
                .build();

        recruitment.addResume(resume);

        resumeRepository.save(resume);
    }

    public List<ResumeResponse> getResumeListByRecruitmentId(Long recruitmentId) {
        List<Resume> resumeList = resumeRepository.findAllByRecruitment_Id(recruitmentId);
        if (resumeList.isEmpty()) {
            throw ResumeListEmptyException.EXCEPTION;
        }

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

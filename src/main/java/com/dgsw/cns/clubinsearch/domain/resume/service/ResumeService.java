package com.dgsw.cns.clubinsearch.domain.resume.service;

import com.dgsw.cns.clubinsearch.domain.recruitment.domain.Recruitment;
import com.dgsw.cns.clubinsearch.domain.recruitment.domain.repository.RecruitmentRepository;
import com.dgsw.cns.clubinsearch.domain.recruitment.exception.RecruitmentNotFoundException;
import com.dgsw.cns.clubinsearch.domain.resume.domain.Resume;
import com.dgsw.cns.clubinsearch.domain.resume.domain.repository.ResumeRepository;
import com.dgsw.cns.clubinsearch.domain.resume.exception.ResumeListEmptyException;
import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.request.SubmitResumeRequest;
import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.response.ResumeResponse;
import com.dgsw.cns.clubinsearch.thirdparty.s3.enums.Dir;
import com.dgsw.cns.clubinsearch.thirdparty.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .build();

        recruitment.addResume(resume);

        resumeRepository.save(resume);
    }

    public List<ResumeResponse> getResumeListByRecruitmentId(Long recruitmentId) {
        List<Resume> resumeList = resumeRepository.findAllByRecruitment_Id(recruitmentId);
        if(resumeList.isEmpty()) {
            throw ResumeListEmptyException.EXCEPTION;
        }
        return resumeList.stream().map(
                it -> new ResumeResponse(it.getId(), it.getName(), it.getStudentNo(), it.getContact(), it.getIntroduction(), it.getLink())
        ).collect(Collectors.toList());
    }

}

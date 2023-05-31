package com.dgsw.cns.clubinsearch.domain.resume.service;

import com.dgsw.cns.clubinsearch.domain.resume.domain.Resume;
import com.dgsw.cns.clubinsearch.domain.resume.domain.repository.ResumeRepository;
import com.dgsw.cns.clubinsearch.domain.resume.exception.NotFoundResumeException;
import com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.request.UpdateResumeStateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateResumeStateService {
    private final ResumeRepository resumeRepository;

     public void execute(UpdateResumeStateRequest request) {
         Resume resume = resumeRepository.findById(request.getId())
                 .orElseThrow(() -> NotFoundResumeException.EXCEPTION);
         resume.updateResumeState(request.getState());
     }


}

package com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.request;

import com.dgsw.cns.clubinsearch.domain.resume.domain.enums.State;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateResumeStateRequest {
    private Long id;
    private State state;
}

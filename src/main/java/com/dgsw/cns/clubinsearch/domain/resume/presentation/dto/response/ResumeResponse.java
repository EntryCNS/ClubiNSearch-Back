package com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.response;

import com.dgsw.cns.clubinsearch.domain.resume.domain.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ResumeResponse {


    private State state;

    private List<MemberResponse> member;

    @AllArgsConstructor @Getter
    static public class MemberResponse {
        private Long resumeId;

        private String name;

        private String studentNo;

        private String contact;

        private String introduction;

        private String link;

        private String fileUrl;
    }

}

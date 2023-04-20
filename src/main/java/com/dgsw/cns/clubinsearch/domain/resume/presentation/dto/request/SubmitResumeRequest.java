package com.dgsw.cns.clubinsearch.domain.resume.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubmitResumeRequest {
    private Long recruitmentId;

    private String name;

    private String studentNo;

    private String contact;

    private String introduction;

    private String link;

    private MultipartFile file;

}

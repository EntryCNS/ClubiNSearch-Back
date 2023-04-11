package com.dgsw.cns.clubinsearch.domain.recruitment.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class RecruitmentNotFoundException extends BusinessException {

    public static final RecruitmentNotFoundException EXCEPTION = new RecruitmentNotFoundException();


    public RecruitmentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "존재 하지 않는 채용 공고");
    }
}

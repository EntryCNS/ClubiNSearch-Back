package com.dgsw.cns.clubinsearch.domain.resume.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ResumeListEmptyException extends BusinessException {
    
    public final static ResumeListEmptyException EXCEPTION = new ResumeListEmptyException();
    
    public ResumeListEmptyException() {
        super(HttpStatus.NOT_FOUND, "조회 결과가 존재하지 않습니다");
    }
}

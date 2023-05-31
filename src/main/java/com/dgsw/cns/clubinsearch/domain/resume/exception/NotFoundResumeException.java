package com.dgsw.cns.clubinsearch.domain.resume.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundResumeException extends BusinessException {

    public static final NotFoundResumeException EXCEPTION = new NotFoundResumeException();

    public NotFoundResumeException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 지원서 입니다");
    }
}

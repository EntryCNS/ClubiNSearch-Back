package com.dgsw.cns.clubinsearch.domain.user.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundUserEmailException extends BusinessException {
    public final static NotFoundUserEmailException EXCEPTION = new NotFoundUserEmailException();

    public NotFoundUserEmailException() {
        super(HttpStatus.BAD_REQUEST, "존재 하지 않는 이메일 입니다");
    }
}

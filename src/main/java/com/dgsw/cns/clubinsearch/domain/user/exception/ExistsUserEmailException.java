package com.dgsw.cns.clubinsearch.domain.user.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ExistsUserEmailException extends BusinessException {
    public final static ExistsUserEmailException EXCEPTION = new ExistsUserEmailException();

    public ExistsUserEmailException() {
        super(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일 입니다");
    }
}

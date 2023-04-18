package com.dgsw.cns.clubinsearch.domain.user.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PasswordNotMatchesException extends BusinessException {
    public final static PasswordNotMatchesException EXCEPTION = new PasswordNotMatchesException();

    public PasswordNotMatchesException() {
        super(HttpStatus.BAD_REQUEST, "유저 비밀번호가 일치 하지 않습니다");
    }
}

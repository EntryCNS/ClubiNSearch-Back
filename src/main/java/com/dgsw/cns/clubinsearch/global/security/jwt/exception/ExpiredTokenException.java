package com.dgsw.cns.clubinsearch.global.security.jwt.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends BusinessException {
    public final static ExpiredTokenException EXCEPTION = new ExpiredTokenException();

    public ExpiredTokenException() {
        super(HttpStatus.UNAUTHORIZED, "만료된 토큰 입니다");
    }
}

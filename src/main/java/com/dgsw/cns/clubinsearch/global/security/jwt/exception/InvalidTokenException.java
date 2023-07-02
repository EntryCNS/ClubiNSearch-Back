package com.dgsw.cns.clubinsearch.global.security.jwt.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends BusinessException {

    public final static InvalidTokenException INVALID_TOKEN_EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰 입니다");
    }
}

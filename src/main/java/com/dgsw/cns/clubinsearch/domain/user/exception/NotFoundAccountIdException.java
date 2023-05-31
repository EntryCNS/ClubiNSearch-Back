package com.dgsw.cns.clubinsearch.domain.user.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundAccountIdException extends BusinessException {
    public static final NotFoundAccountIdException EXCEPTION = new NotFoundAccountIdException();


    public NotFoundAccountIdException() {
        super(HttpStatus.NOT_FOUND, "존재 하지 않는 계정 아이디 입니다");
    }
}

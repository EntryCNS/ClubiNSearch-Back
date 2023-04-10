package com.dgsw.cns.clubinsearch.domain.recruitment.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class RecruitmentsEmptyException extends BusinessException {

    public static final RecruitmentsEmptyException EXCEPTION = new RecruitmentsEmptyException();

    public RecruitmentsEmptyException() {
        super(HttpStatus.NOT_FOUND, "검색 결과가 존재 하지 않습니다");
    }
}

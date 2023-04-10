package com.dgsw.cns.clubinsearch.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException{

    private final HttpStatus httpStatus;

    private final String message;

}

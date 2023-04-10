package com.dgsw.cns.clubinsearch.domain.club.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ClubNotFoundException extends BusinessException {

    public final static ClubNotFoundException EXCEPTION = new ClubNotFoundException();

    public ClubNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "존재하지 않는 동아리 입니다");
    }
}

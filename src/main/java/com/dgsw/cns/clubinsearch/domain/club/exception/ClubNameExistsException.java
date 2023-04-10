package com.dgsw.cns.clubinsearch.domain.club.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ClubNameExistsException extends BusinessException {

    public final static ClubNameExistsException EXCEPTION = new ClubNameExistsException();

    public ClubNameExistsException() {
        super(HttpStatus.BAD_REQUEST, "이미 존재하는 동아리 입니다");
    }
}

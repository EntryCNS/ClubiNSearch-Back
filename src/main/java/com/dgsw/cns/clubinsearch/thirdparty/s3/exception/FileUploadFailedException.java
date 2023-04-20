package com.dgsw.cns.clubinsearch.thirdparty.s3.exception;

import com.dgsw.cns.clubinsearch.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class FileUploadFailedException extends BusinessException {
    public final static FileUploadFailedException Exception = new FileUploadFailedException();

    public FileUploadFailedException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "파일 올리기 싪 ");
    }
}

package com.moira.conotebackend.global.exception.custom;

import com.moira.conotebackend.global.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CoNoteAccountBookException extends RuntimeException {
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public CoNoteAccountBookException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode.getMessage());

        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}

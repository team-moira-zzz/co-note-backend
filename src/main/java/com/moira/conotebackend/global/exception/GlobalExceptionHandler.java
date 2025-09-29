package com.moira.conotebackend.global.exception;

import com.moira.conotebackend.global.exception.custom.CoNoteAccountBookException;
import com.moira.conotebackend.global.exception.custom.CoNoteUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CoNoteUserException.class)
    public ResponseEntity<ErrorResponse> handleCoNoteUserException(CoNoteUserException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(e.getErrorCode())
                .status(e.getHttpStatus())
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(value = CoNoteAccountBookException.class)
    public ResponseEntity<ErrorResponse> handleCoNoteAccountBookException(CoNoteAccountBookException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(e.getErrorCode())
                .status(e.getHttpStatus())
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
    }
}

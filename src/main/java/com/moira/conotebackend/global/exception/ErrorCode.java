package com.moira.conotebackend.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 시스템 관련 에러코드
    INTERNAL_SYSTEM_ERROR("S001", "알 수 없는 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

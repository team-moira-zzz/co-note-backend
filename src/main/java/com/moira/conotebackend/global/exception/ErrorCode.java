package com.moira.conotebackend.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 시스템 관련 에러코드
    INTERNAL_SYSTEM_ERROR("S001", "알 수 없는 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    KAKAO_LOGIN_ERROR("S002", "카카오 로그인 과정에서 알 수 없는 오류가 발생했습니다."),
    KAKAO_LOGIN_TOKEN_ERROR("S003", "카카오 토큰 발급 과정에서 오류가 발생했습니다."),
    KAKAO_LOGIN_USER_INFO_ERROR("S004", "카카오 유저 정보 조회 과정에서 오류가 발생했습니다."),
    NAVER_LOGIN_ERROR("S005", "네이버 로그인 과정에서 알 수 없는 오류가 발생했습니다."),
    NAVER_LOGIN_TOKEN_ERROR("S006", "카카오 토큰 발급 과정에서 오류가 발생했습니다."),
    NAVER_LOGIN_USER_INFO_ERROR("S007", "카카오 유저 정보 조회 과정에서 오류가 발생했습니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

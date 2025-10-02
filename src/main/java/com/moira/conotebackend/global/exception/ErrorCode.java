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
    NAVER_LOGIN_USER_INFO_ERROR("S007", "카카오 유저 정보 조회 과정에서 오류가 발생했습니다."),
    NAVER_LOGIN_STATE_MISMATCH("S008", "요청하신 로그인 상태가 유효하지 않거나 만료되었습니다."),

    // 권한 관련 에러코드
    INVALID_AUTHORIZATION_HEADER("A001", "Authorization 헤더에 토큰 정보가 포함되어 있지 않습니다."),
    EXPIRED_ATK("A002", "Access Token이 만료되었습니다."),
    INVALID_TOKEN1("A003", "토큰 서명이 유효하지 않거나 형식이 올바르지 않습니다."),
    INVALID_TOKEN2("A004", "유효하지 않은 토큰입니다."),

    // 가계부 관련 에러코드
    INVALID_ACCOUNT_BOOK_ENTRY_TYPE("AB001", "올바르지 않은 구분값입니다."),
    INVALID_ACCOUNT_BOOK_ENTRY_METHOD("AB002", "올바르지 않은 결제수단입니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

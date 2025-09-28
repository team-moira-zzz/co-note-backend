package com.moira.conotebackend.domain.user.dto.request.naver;

public record NaverCodeRequest(
        String code,
        String state
) {
}

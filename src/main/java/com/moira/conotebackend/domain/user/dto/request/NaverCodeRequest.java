package com.moira.conotebackend.domain.user.dto.request;

public record NaverCodeRequest(
        String code,
        String state
) {
}

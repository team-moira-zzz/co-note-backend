package com.moira.conotebackend.domain.user.dto.response.naver;

public record NaverResponse(
        String id,

        String nickname,

        String name,

        String email,

        String mobile
) {
}

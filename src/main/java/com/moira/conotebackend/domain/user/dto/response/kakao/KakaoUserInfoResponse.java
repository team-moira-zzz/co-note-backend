package com.moira.conotebackend.domain.user.dto.response.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoUserInfoResponse(
        Long id,

        @JsonProperty("kakao_account")
        KakaoAccount account
) {
}

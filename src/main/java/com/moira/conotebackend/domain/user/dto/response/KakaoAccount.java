package com.moira.conotebackend.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoAccount(
        String email,

        @JsonProperty("profile")
        KakaoProfile profile
) {
}

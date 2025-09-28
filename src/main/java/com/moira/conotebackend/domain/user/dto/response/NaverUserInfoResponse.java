package com.moira.conotebackend.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NaverUserInfoResponse(
        String message,

        @JsonProperty("response")
        NaverResponse response
) {
}

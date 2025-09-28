package com.moira.conotebackend.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.moira.conotebackend.global.constant.CoNoteConstant.KAKAO_AUTH_URL;
import static com.moira.conotebackend.global.constant.CoNoteConstant.NAVER_AUTH_URL;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class SocialLoginRedirectController {
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${kakao.client_id}")
    private String kakaoClientId;

    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;

    @Value("${naver.client_id}")
    private String naverClientId;

    @Value("${naver.redirect_uri}")
    private String naverRedirectUri;

    private void putStateInRedis(String state) {
        redisTemplate.opsForValue().set("naver:state:" + state, true, 5, TimeUnit.MINUTES);
    }

    @GetMapping("/kakao/login-url")
    public String kakaoLoginUrl() {
        // [1] 리다이렉트
        String redirectStringFormat = "%s?response_type=code&client_id=%s&redirect_uri=%s";
        String redirectString = redirectStringFormat.formatted(KAKAO_AUTH_URL, kakaoClientId, kakaoRedirectUri);

        return "redirect:" + redirectString;
    }

    @GetMapping("/naver/login-url")
    public String naverLoginUrl() {
        // [1] state 생성 후 Redis에 대입
        String state = UUID.randomUUID().toString();
        this.putStateInRedis(state);

        // [2] 리다이렉트
        String redirectStringFormat = "%s?response_type=code&client_id=%s&redirect_uri=%s&state=%s";
        String redirectString = redirectStringFormat.formatted(NAVER_AUTH_URL, naverClientId, naverRedirectUri, state);

        return "redirect:" + redirectString;
    }
}

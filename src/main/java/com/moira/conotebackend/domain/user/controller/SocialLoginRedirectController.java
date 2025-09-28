package com.moira.conotebackend.domain.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static com.moira.conotebackend.global.constant.CoNoteConstant.KAKAO_AUTH_URL;
import static com.moira.conotebackend.global.constant.CoNoteConstant.NAVER_AUTH_URL;

@Controller
@RequestMapping("/api/login")
public class SocialLoginRedirectController {
    @Value("${kakao.client_id}")
    private String kakaoClientId;

    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;

    @Value("${naver.client_id}")
    private String naverClientId;

    @Value("${naver.redirect_uri}")
    private String naverRedirectUri;

    @GetMapping("/kakao/login-url")
    public String kakaoLoginUrl() {
        String redirectStringFormat = "%s?response_type=code&client_id=%s&redirect_uri=%s";
        String redirectString = redirectStringFormat.formatted(KAKAO_AUTH_URL, kakaoClientId, kakaoRedirectUri);

        return "redirect:" + redirectString;
    }

    @GetMapping("/naver/login-url")
    public String naverLoginUrl() {
        String state = UUID.randomUUID().toString();
        String redirectStringFormat = "%s?response_type=code&client_id=%s&redirect_uri=%s&state=%s";
        String redirectString = redirectStringFormat.formatted(NAVER_AUTH_URL, naverClientId, naverRedirectUri, state);

        return "redirect:" + redirectString;
    }
}

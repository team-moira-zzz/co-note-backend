package com.moira.conotebackend.domain.user.controller;

import com.moira.conotebackend.domain.user.service.SocialLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/login")
public class SocialLoginController {
    private final SocialLoginService socialLoginService;

    @GetMapping("/kakao/oauth")
    void kakaoLogin(@RequestParam String code) {
        socialLoginService.kakaoLogin(code);
    }
}

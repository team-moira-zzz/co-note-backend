package com.moira.conotebackend.domain.user.controller;

import com.moira.conotebackend.domain.user.dto.request.KakaoCodeRequest;
import com.moira.conotebackend.domain.user.dto.response.TokenResponse;
import com.moira.conotebackend.domain.user.service.SocialLoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.moira.conotebackend.global.constant.CoNoteConstant.RTK_COOKIE_NAME;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/login")
public class SocialLoginController {
    private final SocialLoginService socialLoginService;

    private void putRtkInCookie(HttpServletResponse response, String rtk) {
        Cookie cookie = new Cookie(RTK_COOKIE_NAME, rtk);

        // cookie.setSecure(true);         // HTTPS 연결에서만 전송 (운영 환경에서는 주석 해제)
        cookie.setHttpOnly(true);          // JavaScript로 접근 불가능
        cookie.setPath("/");               // 모든 경로에서 쿠키 사용 가능
        cookie.setMaxAge(60 * 60 * 24);    // 1일

        response.addCookie(cookie);
    }

    @PostMapping("/kakao")
    ResponseEntity<String> kakaoLogin(
            @RequestBody KakaoCodeRequest request,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        // [1] IP 추출
        String ipAddress = httpServletRequest.getRemoteAddr();

        // [2] 로그인 성공 후 atk, rtk 반환
        TokenResponse tokens = socialLoginService.kakaoLogin(request.code(), ipAddress);

        // [3] rtk는 쿠키에 넣어준다.
        this.putRtkInCookie(httpServletResponse, tokens.rtk());

        // [4] atk는 요청 본문으로 반환한다.
        return ResponseEntity.ok().body(tokens.atk());
    }

    @GetMapping("/naver/oauth")
    ResponseEntity<String> naverLogin(
            @RequestParam String code,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // [1] IP 추출
        String ipAddress = request.getRemoteAddr();

        // [2] 로그인 성공 후 atk, rtk 반환
        TokenResponse tokens = socialLoginService.naverLogin(code, ipAddress);

        // [3] rtk는 쿠키에 넣어준다.
        this.putRtkInCookie(response, tokens.rtk());

        // [4] atk는 요청 본문으로 반환한다.
        return ResponseEntity.ok().body(tokens.atk());
    }
}

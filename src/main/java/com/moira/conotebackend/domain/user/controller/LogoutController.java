package com.moira.conotebackend.domain.user.controller;

import com.moira.conotebackend.domain.user.service.LogoutService;
import com.moira.conotebackend.global.auth.SimpleUserAuth;
import com.moira.conotebackend.global.auth.UserPrincipal;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.moira.conotebackend.global.constant.CoNoteConstant.RTK_COOKIE_NAME;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class LogoutController {
    private final LogoutService logoutService;

    private void removeRtkFromCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(RTK_COOKIE_NAME, null);

        // cookie.setSecure(true);         // HTTPS 연결에서만 전송 (운영 환경에서는 주석 해제)
        cookie.setHttpOnly(true);          // JavaScript로 접근 불가능
        cookie.setPath("/");               // 모든 경로에서 쿠키 사용 가능
        cookie.setMaxAge(0);               // 쿠키 만료

        response.addCookie(cookie);
    }

    @PostMapping("/logout")
    ResponseEntity<Object> logout(
            @UserPrincipal SimpleUserAuth simpleUserAuth,
            HttpServletResponse httpServletResponse
    ) {
        // [1] DB에서 rtk를 삭제
        logoutService.logout(simpleUserAuth);

        // [2] 쿠키에 담긴 rtk를 초기화
        this.removeRtkFromCookie(httpServletResponse);

        return ResponseEntity.ok().body(null);
    }
}

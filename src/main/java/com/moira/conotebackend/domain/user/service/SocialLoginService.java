package com.moira.conotebackend.domain.user.service;

import com.moira.conotebackend.domain.user.component.OauthRequestSender;
import com.moira.conotebackend.domain.user.dto.response.KakaoUserInfoResponse;
import com.moira.conotebackend.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialLoginService {
    private final OauthRequestSender oauthRequestSender;
    private final UserMapper userMapper;

    public void kakaoLogin(String code) {
        // [1] 유저 정보 조회
        String kakaoAtk = oauthRequestSender.getKakaoToken(code);
        KakaoUserInfoResponse userInfo = oauthRequestSender.getKakaoUserInfo(kakaoAtk);

        String email = userInfo.account().email();
        String nickname = userInfo.account().profile().nickname();

        // [2-1] 신규회원일 경우 회원가입
        if (userMapper.checkEmail(email) < 1) {

        }
        // [2-2] 기존회원일 경우 로그인
        else {

        }
    }
}

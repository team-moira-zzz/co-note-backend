package com.moira.conotebackend.domain.user.service;

import com.moira.conotebackend.domain.user.component.OauthRequestSender;
import com.moira.conotebackend.domain.user.dto.response.KakaoUserInfoResponse;
import com.moira.conotebackend.domain.user.dto.response.NaverUserInfoResponse;
import com.moira.conotebackend.domain.user.dto.response.TokenResponse;
import com.moira.conotebackend.domain.user.entity.User;
import com.moira.conotebackend.domain.user.entity.UserLoginHistory;
import com.moira.conotebackend.domain.user.entity.UserType;
import com.moira.conotebackend.domain.user.mapper.UserMapper;
import com.moira.conotebackend.global.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SocialLoginService {
    private final JwtProvider jwtProvider;
    private final OauthRequestSender oauthRequestSender;
    private final UserMapper userMapper;

    private TokenResponse getTokens(User user) {
        String atk = jwtProvider.createAtk(user);
        String rtk = jwtProvider.createRtk(user);

        return new TokenResponse(atk, rtk);
    }

    @Transactional
    public TokenResponse kakaoLogin(String code, String ipAddress) {
        // [1] 유저 정보 조회
        String kakaoAtk = oauthRequestSender.getKakaoToken(code);
        KakaoUserInfoResponse userInfo = oauthRequestSender.getKakaoUserInfo(kakaoAtk);

        String email = userInfo.account().email();
        String nickname = userInfo.account().profile().nickname();
        User user = userMapper.getUserByEmail(email);

        // [2-1] 신규회원일 경우 회원가입
        if (user == null) {
            User newUser = new User(email, nickname, UserType.KAKAO);
            userMapper.signup(newUser);

            user = newUser;
        }

        // [2-2] 기존회원일 경우 로그인 수행 (토큰 생성)
        TokenResponse tokens = getTokens(user);

        // [3] 로그인 기록 저장
        UserLoginHistory userLoginHistory = new UserLoginHistory(user, ipAddress);
        userMapper.updateUserLoginInfo(user.getId(), tokens.rtk());
        userMapper.insertUserLoginHistory(userLoginHistory);

        return tokens;
    }

    @Transactional
    public TokenResponse naverLogin(String code, String ipAddress) {
        // [1] 유저 정보 조회
        String state = UUID.randomUUID().toString();
        String naverAtk = oauthRequestSender.getNaverToken(code, state);
        NaverUserInfoResponse userInfo = oauthRequestSender.getNaverUserInfo(naverAtk);

        String email = userInfo.response().email();
        String name = userInfo.response().name();
        String nickname = userInfo.response().nickname();
        String phone = userInfo.response().mobile();

        User user = userMapper.getUserByEmail(email);

        // [2-1] 신규회원일 경우 회원가입
        if (user == null) {
            User newUser = new User(email, name, nickname, phone, UserType.NAVER);
            userMapper.signup(newUser);

            user = newUser;
        }

        // [2-2] 기존회원일 경우 로그인 수행 (토큰 생성)
        TokenResponse tokens = getTokens(user);

        // [3] 로그인 기록 저장
        UserLoginHistory userLoginHistory = new UserLoginHistory(user, ipAddress);
        userMapper.updateUserLoginInfo(user.getId(), tokens.rtk());
        userMapper.insertUserLoginHistory(userLoginHistory);

        return tokens;
    }
}

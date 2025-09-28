package com.moira.conotebackend.domain.user.service;

import com.moira.conotebackend.domain.user.component.OauthRequestSender;
import com.moira.conotebackend.domain.user.dto.response.kakao.KakaoUserInfoResponse;
import com.moira.conotebackend.domain.user.dto.response.naver.NaverUserInfoResponse;
import com.moira.conotebackend.domain.user.dto.response.TokenResponse;
import com.moira.conotebackend.domain.user.entity.User;
import com.moira.conotebackend.domain.user.entity.UserLoginHistory;
import com.moira.conotebackend.domain.user.entity.UserType;
import com.moira.conotebackend.domain.user.mapper.UserMapper;
import com.moira.conotebackend.global.auth.JwtProvider;
import com.moira.conotebackend.global.exception.ErrorCode;
import com.moira.conotebackend.global.exception.custom.CoNoteUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SocialLoginService {
    private final JwtProvider jwtProvider;
    private final OauthRequestSender oauthRequestSender;
    private final RedisTemplate<String, Object> redisTemplate;
    private final UserMapper userMapper;

    private TokenResponse getTokens(User user) {
        String atk = jwtProvider.createAtk(user);
        String rtk = jwtProvider.createRtk(user);

        return new TokenResponse(atk, rtk);
    }

    private void validateState(String state) {
        Object stateInRedis = redisTemplate.opsForValue().getAndDelete("naver:state:" + state);

        if (stateInRedis == null) {
            throw new CoNoteUserException(ErrorCode.NAVER_LOGIN_STATE_MISMATCH, HttpStatus.UNAUTHORIZED);
        }
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
    public TokenResponse naverLogin(String code, String state, String ipAddress) {
        // [1] state 검증
        this.validateState(state);

        // [2] 유저 정보 조회
        String naverAtk = oauthRequestSender.getNaverToken(code, state);
        NaverUserInfoResponse userInfo = oauthRequestSender.getNaverUserInfo(naverAtk);

        String email = userInfo.response().email();
        String name = userInfo.response().name();
        String nickname = userInfo.response().nickname();
        String phone = userInfo.response().mobile();

        User user = userMapper.getUserByEmail(email);

        // [3-1] 신규회원일 경우 회원가입
        if (user == null) {
            User newUser = new User(email, name, nickname, phone, UserType.NAVER);
            userMapper.signup(newUser);

            user = newUser;
        }

        // [3-2] 기존회원일 경우 로그인 수행 (토큰 생성)
        TokenResponse tokens = this.getTokens(user);

        // [4] 로그인 기록 저장
        UserLoginHistory userLoginHistory = new UserLoginHistory(user, ipAddress);
        userMapper.updateUserLoginInfo(user.getId(), tokens.rtk());
        userMapper.insertUserLoginHistory(userLoginHistory);

        return tokens;
    }
}

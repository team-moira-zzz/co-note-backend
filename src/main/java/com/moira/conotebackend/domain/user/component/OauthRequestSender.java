package com.moira.conotebackend.domain.user.component;

import com.moira.conotebackend.domain.user.dto.response.KakaoTokenResponse;
import com.moira.conotebackend.domain.user.dto.response.KakaoUserInfoResponse;
import com.moira.conotebackend.global.exception.ErrorCode;
import com.moira.conotebackend.global.exception.custom.CoNoteUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.moira.conotebackend.global.constant.CoNoteConstant.KAKAO_TOKEN_URL;
import static com.moira.conotebackend.global.constant.CoNoteConstant.KAKAO_USER_INFO_URL;

@Component
@RequiredArgsConstructor
@Slf4j
public class OauthRequestSender {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${kakao.client_id}")
    private String CLIENT_ID;

    @Value("${kakao.redirect_uri}")
    private String REDIRECT_URI;

    private HttpEntity<MultiValueMap<String, String>> getHttpEntity(String code) {
        // [1] Http Header
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

        // [2] Http Body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", code);
        body.add("redirect_uri", REDIRECT_URI);
        body.add("client_id", CLIENT_ID);

        // [3] Http Header + Http Body
        return new HttpEntity<>(body, headers);
    }

    private HttpEntity<MultiValueMap<String, String>> getHttpEntity2(String accessToken) {
        // [1] Http Header
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

        // [2] Http Body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        // [3] Http Header + Http Body
        return new HttpEntity<>(body, headers);
    }

    public String getKakaoToken(String code) {
        // [1] Http 구조 생성
        HttpEntity<MultiValueMap<String, String>> request = getHttpEntity(code);

        try {
            // [2] 카카오 API로 POST 요청 전송
            ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                    KAKAO_TOKEN_URL,
                    HttpMethod.POST,
                    request,
                    KakaoTokenResponse.class
            );

            // [3] 응답에서 AccessToken 추출
            if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
                return response.getBody().accessToken();
            } else {
                log.error(
                        "[카카오 토큰 발급 오류] {}. HTTP Status: {}, Response Body: {}",
                        ErrorCode.KAKAO_LOGIN_TOKEN_ERROR.getMessage(),
                        response.getStatusCode(),
                        response.getBody()
                );
                throw new CoNoteUserException(ErrorCode.KAKAO_LOGIN_TOKEN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("[카카오 로그인 오류] {}", ErrorCode.KAKAO_LOGIN_ERROR.getMessage(), e);

            throw new CoNoteUserException(ErrorCode.KAKAO_LOGIN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public KakaoUserInfoResponse getKakaoUserInfo(String accessToken) {
        // [1] Http 구조 생성
        HttpEntity<MultiValueMap<String, String>> request = getHttpEntity2(accessToken);

        try {
            // [2] 카카오 API로 POST 요청 전송
            ResponseEntity<KakaoUserInfoResponse> response = restTemplate.exchange(
                    KAKAO_USER_INFO_URL,
                    HttpMethod.POST,
                    request,
                    KakaoUserInfoResponse.class
            );

            // [3] 응답에서 유저 정보 추출
            if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                log.error(
                        "[카카오 유저 정보 조회 오류] {}. HTTP Status: {}, Response Body: {}",
                        ErrorCode.KAKAO_LOGIN_USER_INFO_ERROR.getMessage(),
                        response.getStatusCode(),
                        response.getBody()
                );
                throw new CoNoteUserException(ErrorCode.KAKAO_LOGIN_USER_INFO_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("[카카오 로그인 오류] {}", ErrorCode.KAKAO_LOGIN_ERROR.getMessage(), e);

            throw new CoNoteUserException(ErrorCode.KAKAO_LOGIN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

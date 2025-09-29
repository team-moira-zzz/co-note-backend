package com.moira.conotebackend.global.constant;

import java.util.List;

public class CoNoteConstant {
    // 로그인 관련
    public final static String RTK_COOKIE_NAME = "refreshToken";

    public final static String KAKAO_AUTH_URL = "https://kauth.kakao.com/oauth/authorize";
    public final static String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    public final static String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me?secure_resource=true";

    public final static String NAVER_AUTH_URL = "https://nid.naver.com/oauth2.0/authorize";
    public final static String NAVER_TOKEN_URL = "https://nid.naver.com/oauth2.0/token";
    public final static String NAVER_USER_INFO_URL = "https://openapi.naver.com/v1/nid/me";

    // 그룹 관련
    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int CODE_LENGTH = 8;

    public static final List<String> DEFAULT_CATEGORY_NAMES = List.of("식비", "교통비", "데이트");
}

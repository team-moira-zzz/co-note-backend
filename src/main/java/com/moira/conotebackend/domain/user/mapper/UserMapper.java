package com.moira.conotebackend.domain.user.mapper;

import com.moira.conotebackend.domain.user.entity.User;
import com.moira.conotebackend.domain.user.entity.UserLoginHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 회원가입
    void signup(User user);

    // 로그인
    User getUserByEmail(String email);

    void updateUserLoginInfo(String id, String rtk);

    void insertUserLoginHistory(UserLoginHistory userLoginHistory);

    // 로그아웃
    void updateUserLogoutInfo(String userId);
}

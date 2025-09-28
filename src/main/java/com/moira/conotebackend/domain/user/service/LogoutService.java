package com.moira.conotebackend.domain.user.service;

import com.moira.conotebackend.domain.user.mapper.UserMapper;
import com.moira.conotebackend.global.auth.SimpleUserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogoutService {
    private final UserMapper userMapper;

    @Transactional
    public void logout(SimpleUserAuth simpleUserAuth) {
        String userId = simpleUserAuth.userId();

        // rtk 삭제
        userMapper.updateUserLogoutInfo(userId);
    }
}

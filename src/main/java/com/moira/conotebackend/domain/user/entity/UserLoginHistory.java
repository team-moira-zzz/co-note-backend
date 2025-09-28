package com.moira.conotebackend.domain.user.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserLoginHistory {
    private String id;
    private String userId;
    private String ipAddress;
    private UserType loginType;
    private LocalDateTime loginAt;
    private LocalDateTime logoutAt;

    public UserLoginHistory(User user, String ipAddress) {
        this.id = UUID.randomUUID().toString();
        this.userId = user.getId();
        this.ipAddress = ipAddress;
        this.loginType = user.getType();
        this.loginAt = LocalDateTime.now();
        this.logoutAt = null;
    }
}
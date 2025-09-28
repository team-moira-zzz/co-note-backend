package com.moira.conotebackend.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private String id;
    private UserRole role;
    private UserStatus status;
    private UserType type;
    private String email;
    private String name;
    private String nickname;
    private String phone;
    private String rtk;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String email, String nickname, UserType type) {
        this.id = UUID.randomUUID().toString();
        this.role = UserRole.USER;
        this.status = UserStatus.ACTIVE;
        this.type = type;
        this.email = email;
        this.name = null;
        this.nickname = nickname;
        this.phone = null;
        this.rtk = null;
        this.lastLoginAt = null;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public User(String email, String name, String nickname, String phone, UserType type) {
        this.id = UUID.randomUUID().toString();
        this.role = UserRole.USER;
        this.status = UserStatus.ACTIVE;
        this.type = type;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.rtk = null;
        this.lastLoginAt = null;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}

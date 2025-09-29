package com.moira.conotebackend.domain.book.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountBookGroupUser {
    private String groupId;
    private String userId;
    private LocalDateTime joinedAt;
    private LocalDateTime leftAt;

    public AccountBookGroupUser(String groupId, String userId) {
        this.groupId = groupId;
        this.userId = userId;
        this.joinedAt = LocalDateTime.now();
        this.leftAt = null;
    }
}

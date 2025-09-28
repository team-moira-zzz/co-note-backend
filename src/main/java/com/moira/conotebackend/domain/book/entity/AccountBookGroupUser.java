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
}

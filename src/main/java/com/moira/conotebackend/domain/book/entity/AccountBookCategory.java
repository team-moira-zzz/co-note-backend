package com.moira.conotebackend.domain.book.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountBookCategory {
    private Long id;
    private String groupId;
    private String name;
    private LocalDateTime createdAt;

    public AccountBookCategory(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }
}
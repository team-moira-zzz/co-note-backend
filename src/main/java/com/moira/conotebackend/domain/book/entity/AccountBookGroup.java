package com.moira.conotebackend.domain.book.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountBookGroup {
    private String id;
    private String userId;
    private String name;
    private String code;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
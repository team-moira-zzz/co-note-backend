package com.moira.conotebackend.domain.book.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountBookEntry {
    private String id;
    private String userId;
    private String groupId;
    private Long categoryId;
    private String content;
    private String description;
    private Integer price;
    private LocalDate date;
    private AccountBookEntryType type;
    private AccountBookEntryMethod method;
    private LocalDateTime createdAt;
}
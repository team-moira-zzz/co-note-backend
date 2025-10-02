package com.moira.conotebackend.domain.book.entity;

import com.moira.conotebackend.domain.book.dto.request.AccountBookEntryAddRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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

    public AccountBookEntry(String userId, String groupId, AccountBookEntryAddRequest request) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.groupId = groupId;
        this.categoryId = request.categoryId();
        this.content = request.content();
        this.description = request.description();
        this.price = request.price();
        this.date = request.date();
        this.type = AccountBookEntryType.valueOf(request.type());
        this.method = AccountBookEntryMethod.valueOf(request.method());
        this.createdAt = LocalDateTime.now();

    }
}
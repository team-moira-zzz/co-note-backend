package com.moira.conotebackend.domain.book.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyAccountEntryResponse {
    // 가계부 항목 정보
    private Long id;
    private String type;
    private String content;
    private String description;
    private Long price;

    // 카테고리 정보
    private Long categoryId;
    private String categoryName;

    // 유저 정보
    private String userId;
    private String userName;
}

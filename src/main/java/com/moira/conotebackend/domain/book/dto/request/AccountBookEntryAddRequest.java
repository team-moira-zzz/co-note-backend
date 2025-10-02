package com.moira.conotebackend.domain.book.dto.request;

import java.time.LocalDate;

public record AccountBookEntryAddRequest(
        Long categoryId,
        String content,
        String description,
        Integer price,
        LocalDate date,
        String type,
        String method
) {
}

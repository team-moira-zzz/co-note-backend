package com.moira.conotebackend.domain.book.mapper;

import com.moira.conotebackend.domain.book.dto.response.CategoryResponse;
import com.moira.conotebackend.domain.book.dto.response.DailyAccountEntryResponse;
import com.moira.conotebackend.domain.book.entity.AccountBookCategory;
import com.moira.conotebackend.domain.book.entity.AccountBookEntry;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AccountBookMapper {
    // 카테고리 생성
    void insertCategory(AccountBookCategory accountBookCategory);

    // 카테고리 목록 조회
    List<CategoryResponse> getCategoryList(String groupId);

    // 일별 가계부 항목 조회
    List<DailyAccountEntryResponse> getDailyEntryList(String groupId, LocalDate date);

    // 가계부 항목 저장
    void insertEntry(AccountBookEntry accountBookEntry);
}

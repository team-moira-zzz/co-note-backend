package com.moira.conotebackend.domain.book.mapper;

import com.moira.conotebackend.domain.book.dto.response.DailyAccountEntryResponse;
import com.moira.conotebackend.domain.book.entity.AccountBookCategory;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AccountBookMapper {
    void insertCategory(AccountBookCategory accountBookCategory);

    List<DailyAccountEntryResponse> getDailyEntryList(String groupId, LocalDateTime date);
}

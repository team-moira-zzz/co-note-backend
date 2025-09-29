package com.moira.conotebackend.domain.book.mapper;

import com.moira.conotebackend.domain.book.entity.AccountBookCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountBookMapper {
    void insertCategory(AccountBookCategory accountBookCategory);
}

package com.moira.conotebackend.domain.book.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountBookGroupMapper {
    // 그룹 가입 여부 조회
    int selectGroupCnt(String userId);
}

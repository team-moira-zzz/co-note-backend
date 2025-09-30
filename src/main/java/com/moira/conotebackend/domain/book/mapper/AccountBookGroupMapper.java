package com.moira.conotebackend.domain.book.mapper;

import com.moira.conotebackend.domain.book.entity.AccountBookGroup;
import com.moira.conotebackend.domain.book.entity.AccountBookGroupUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountBookGroupMapper {
    // 그룹 가입 여부 조회
    int selectGroupCnt(String userId);

    // 가입 그룹 ID 조회
    String selectGroupId(String userId);
    
    // 그룹 생성 및 가입
    void createAccountBookGroup(AccountBookGroup accountBookGroup);

    void insertAccountBookGroupUser(AccountBookGroupUser accountBookGroupUser);
}

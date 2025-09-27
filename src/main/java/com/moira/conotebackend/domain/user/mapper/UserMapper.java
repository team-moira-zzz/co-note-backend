package com.moira.conotebackend.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 회원가입
    int checkEmail(String email);
}

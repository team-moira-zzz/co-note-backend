package com.moira.conotebackend.domain.book.service;

import com.moira.conotebackend.domain.book.mapper.AccountBookGroupMapper;
import com.moira.conotebackend.global.auth.SimpleUserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountBookGroupService {
    private final AccountBookGroupMapper accountBookGroupMapper;

    public int getUserGroupCount(SimpleUserAuth simpleUserAuth) {
        String userId = simpleUserAuth.userId();

        return accountBookGroupMapper.selectGroupCnt(userId);
    }
}

package com.moira.conotebackend.domain.book.service;

import com.moira.conotebackend.domain.book.dto.response.DailyAccountEntryResponse;
import com.moira.conotebackend.domain.book.entity.AccountBookCategory;
import com.moira.conotebackend.domain.book.mapper.AccountBookGroupMapper;
import com.moira.conotebackend.domain.book.mapper.AccountBookMapper;
import com.moira.conotebackend.global.auth.SimpleUserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.moira.conotebackend.global.constant.CoNoteConstant.DEFAULT_CATEGORY_NAMES;


@RequiredArgsConstructor
@Service
public class AccountBookService {
    private final AccountBookMapper accountBookMapper;
    private final AccountBookGroupMapper accountBookGroupMapper;

    @Transactional
    public void insertDefaultCategories(String groupId) {
        for (String name : DEFAULT_CATEGORY_NAMES) {
            AccountBookCategory accountBookCategory = new AccountBookCategory(groupId, name);

            accountBookMapper.insertCategory(accountBookCategory);
        }
    }

    @Transactional(readOnly = true)
    public List<DailyAccountEntryResponse> getDailyAccountBookEntryList(SimpleUserAuth simpleUserAuth, LocalDateTime date) {
        // 가입된 그룹의 ID 조회
        String userId = simpleUserAuth.userId();
        String groupId = accountBookGroupMapper.selectGroupId(userId);

        //
        return accountBookMapper.getDailyEntryList(groupId, date);
    }

}

package com.moira.conotebackend.domain.book.service;

import com.moira.conotebackend.domain.book.entity.AccountBookCategory;
import com.moira.conotebackend.domain.book.mapper.AccountBookMapper;
import com.moira.conotebackend.global.constant.CoNoteConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.moira.conotebackend.global.constant.CoNoteConstant.DEFAULT_CATEGORY_NAMES;


@RequiredArgsConstructor
@Service
public class AccountBookService {
    private final AccountBookMapper accountBookMapper;

    @Transactional
    void insertDefaultCategories(String groupId) {
        for (String name : DEFAULT_CATEGORY_NAMES) {
            AccountBookCategory accountBookCategory = new AccountBookCategory(groupId, name);

            accountBookMapper.insertCategory(accountBookCategory);
        }
    }

}

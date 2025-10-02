package com.moira.conotebackend.domain.book.service;

import com.moira.conotebackend.domain.book.dto.request.AccountBookEntryAddRequest;
import com.moira.conotebackend.domain.book.dto.response.CategoryResponse;
import com.moira.conotebackend.domain.book.dto.response.DailyAccountEntryResponse;
import com.moira.conotebackend.domain.book.entity.AccountBookCategory;
import com.moira.conotebackend.domain.book.entity.AccountBookEntry;
import com.moira.conotebackend.domain.book.entity.AccountBookEntryMethod;
import com.moira.conotebackend.domain.book.entity.AccountBookEntryType;
import com.moira.conotebackend.domain.book.mapper.AccountBookGroupMapper;
import com.moira.conotebackend.domain.book.mapper.AccountBookMapper;
import com.moira.conotebackend.global.auth.SimpleUserAuth;
import com.moira.conotebackend.global.exception.ErrorCode;
import com.moira.conotebackend.global.exception.custom.CoNoteAccountBookException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.moira.conotebackend.global.constant.CoNoteConstant.DEFAULT_CATEGORY_NAMES;


@RequiredArgsConstructor
@Service
public class AccountBookService {
    private final AccountBookMapper accountBookMapper;
    private final AccountBookGroupMapper accountBookGroupMapper;

    private void validate(AccountBookEntryAddRequest request) {
        // TODO: 카테고리 존재 여부 확인

        // 항목 구분값 (enum) 검사
        try {
            AccountBookEntryType.valueOf(request.type().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CoNoteAccountBookException(ErrorCode.INVALID_ACCOUNT_BOOK_ENTRY_TYPE, HttpStatus.BAD_REQUEST);
        }

        // 결제 수단 (enum) 검사
        try {
            AccountBookEntryMethod.valueOf(request.method().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CoNoteAccountBookException(ErrorCode.INVALID_ACCOUNT_BOOK_ENTRY_METHOD, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public void insertDefaultCategories(String groupId) {
        for (String name : DEFAULT_CATEGORY_NAMES) {
            AccountBookCategory accountBookCategory = new AccountBookCategory(groupId, name);

            accountBookMapper.insertCategory(accountBookCategory);
        }
    }

    @Transactional(readOnly = false)
    public List<CategoryResponse> getCategoryList(SimpleUserAuth simpleUserAuth) {
        // 가입된 그룹의 ID 조회
        String userId = simpleUserAuth.userId();
        String groupId = accountBookGroupMapper.selectGroupId(userId);

        // 해당 그룹의 카테고리 목록 조회
        return accountBookMapper.getCategoryList(groupId);
    }

    @Transactional(readOnly = true)
    public List<DailyAccountEntryResponse> getDailyAccountBookEntryList(SimpleUserAuth simpleUserAuth, LocalDate date) {
        // 가입된 그룹의 ID 조회
        String userId = simpleUserAuth.userId();
        String groupId = accountBookGroupMapper.selectGroupId(userId);

        // 일별 가계부 항목 조회
        return accountBookMapper.getDailyEntryList(groupId, date);
    }

    @Transactional
    public void insertAccountBookEntry(SimpleUserAuth simpleUserAuth, AccountBookEntryAddRequest request) {
        // 가입된 그룹의 ID 조회
        String userId = simpleUserAuth.userId();
        String groupId = accountBookGroupMapper.selectGroupId(userId);

        // 유효성 검사
        this.validate(request);

        // 가계부 항목 insert
        AccountBookEntry accountBookEntry = new AccountBookEntry(userId, groupId, request);
        accountBookMapper.insertEntry(accountBookEntry);
    }

}

package com.moira.conotebackend.domain.book.service;

import com.moira.conotebackend.domain.book.dto.request.AccountBookCreateRequest;
import com.moira.conotebackend.domain.book.entity.AccountBookGroup;
import com.moira.conotebackend.domain.book.entity.AccountBookGroupUser;
import com.moira.conotebackend.domain.book.mapper.AccountBookGroupMapper;
import com.moira.conotebackend.global.auth.SimpleUserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

import static com.moira.conotebackend.global.constant.CoNoteConstant.CHARACTERS;
import static com.moira.conotebackend.global.constant.CoNoteConstant.CODE_LENGTH;

@RequiredArgsConstructor
@Service
public class AccountBookGroupService {
    private final AccountBookGroupMapper accountBookGroupMapper;
    private final AccountBookService accountBookService;
    private static final SecureRandom RANDOM = new SecureRandom();

    private String generateRandomCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        return sb.toString();
    }

    @Transactional(readOnly = true)
    public int getUserGroupCount(SimpleUserAuth simpleUserAuth) {
        String userId = simpleUserAuth.userId();

        return accountBookGroupMapper.selectGroupCnt(userId);
    }

    @Transactional
    public void createAccountBookGroup(AccountBookCreateRequest request, SimpleUserAuth simpleUserAuth) {
        String userId = simpleUserAuth.userId();
        String code = this.generateRandomCode();

        // DTO -> 엔티티 변환
        AccountBookGroup accountBookGroup = new AccountBookGroup(request, userId, code);
        AccountBookGroupUser accountBookGroupUser = new AccountBookGroupUser(accountBookGroup.getId(), userId);

        // DB 저장
        accountBookGroupMapper.createAccountBookGroup(accountBookGroup);
        accountBookGroupMapper.insertAccountBookGroupUser(accountBookGroupUser);

        // 그룹 내 기본 카테고리 생성
        accountBookService.insertDefaultCategories(accountBookGroup.getId());
    }
}

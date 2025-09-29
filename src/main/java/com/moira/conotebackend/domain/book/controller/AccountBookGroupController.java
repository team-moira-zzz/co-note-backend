package com.moira.conotebackend.domain.book.controller;

import com.moira.conotebackend.domain.book.service.AccountBookGroupService;
import com.moira.conotebackend.global.auth.SimpleUserAuth;
import com.moira.conotebackend.global.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/book/group")
@RestController
public class AccountBookGroupController {
    private final AccountBookGroupService accountBookGroupService;

    @GetMapping("/count")
    ResponseEntity<Integer> getUserGroupCount(@UserPrincipal SimpleUserAuth simpleUserAuth) {
        int count = accountBookGroupService.getUserGroupCount(simpleUserAuth);

        return ResponseEntity.ok().body(count);
    }
}

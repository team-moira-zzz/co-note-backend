package com.moira.conotebackend.domain.book.controller;

import com.moira.conotebackend.domain.book.dto.request.AccountBookEntryAddRequest;
import com.moira.conotebackend.domain.book.dto.response.CategoryResponse;
import com.moira.conotebackend.domain.book.dto.response.DailyAccountEntryResponse;
import com.moira.conotebackend.domain.book.service.AccountBookService;
import com.moira.conotebackend.global.auth.SimpleUserAuth;
import com.moira.conotebackend.global.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/book")
@RestController
public class AccountBookController {
    private final AccountBookService accountBookService;

    @GetMapping("/categories")
    ResponseEntity<List<CategoryResponse>> getCategoryList(@UserPrincipal SimpleUserAuth simpleUserAuth) {
        List<CategoryResponse> list = accountBookService.getCategoryList(simpleUserAuth);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/entries/daily")
    ResponseEntity<List<DailyAccountEntryResponse>> getDailyAccountBookEntryList(
            @UserPrincipal SimpleUserAuth simpleUserAuth,
            @RequestParam LocalDate date
    ) {
        List<DailyAccountEntryResponse> list = accountBookService.getDailyAccountBookEntryList(simpleUserAuth, date);

        return ResponseEntity.ok(list);
    }

    @PostMapping("/entries")
    ResponseEntity<Object> insertAccountBookEntry(
            @UserPrincipal SimpleUserAuth simpleUserAuth,
            @RequestBody AccountBookEntryAddRequest request
    ) {
        accountBookService.insertAccountBookEntry(simpleUserAuth, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}

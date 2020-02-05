package com.alimmit.ledger.api.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    public static final String PATH = "/account";

    private final AccountService accountService;

    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(PATH)
    @ResponseStatus(HttpStatus.CREATED)
    public AccountBean create(@RequestBody AccountBean account) {
        return accountService.create(account);
    }

    @GetMapping(PATH)
    public Page<AccountBean> page(@PageableDefault final Pageable pageable) {
        return accountService.page(pageable);
    }
}

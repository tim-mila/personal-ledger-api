package com.alimmit.ledger.api.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}

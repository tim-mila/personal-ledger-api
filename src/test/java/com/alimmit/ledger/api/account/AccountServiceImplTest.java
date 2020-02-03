package com.alimmit.ledger.api.account;

import com.alimmit.ledger.api.util.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.when;

class AccountServiceImplTest {

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        final AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        when(accountRepository.findByName("Existing Account")).thenReturn(Optional.of(Account.create("Existing Account")));
        when(accountRepository.findByName("New Account")).thenReturn(Optional.empty());

        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    void create() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> accountService.create(AccountBean.create("Existing Account", StringUtils.EMPTY)));
    }
}
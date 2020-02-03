package com.alimmit.ledger.api.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business service dealing with {@link Account} operations
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public AccountBean create(final AccountBean accountBean) {

        if (accountRepository.findByName(accountBean.getName()).isPresent()) {
            throw new IllegalArgumentException("Account with name already exists");
        }

        return AccountBean.from(accountRepository.save(Account.create(accountBean.getName(), accountBean.getDescription())));
    }
}

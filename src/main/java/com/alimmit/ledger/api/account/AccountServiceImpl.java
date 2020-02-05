package com.alimmit.ledger.api.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public Page<AccountBean> page(final Pageable pageable) {

        final Page<Account> page = accountRepository.findAll(pageable);
        final List<AccountBean> transformed = page.stream().map(AccountBean::from).collect(Collectors.toList());
        return new PageImpl<>(transformed, page.getPageable(), page.getTotalElements());
    }
}

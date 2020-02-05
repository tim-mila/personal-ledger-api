package com.alimmit.ledger.api.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Business service dealing with {@link Account} operations.  This interface is the API for {@link Account} operations
 * to the rest of the application
 */
public interface AccountService {

    /**
     * Create a new {@link Account}
     *
     * @param accountBean {@link AccountBean} containing account input
     * @return AccountBean representing created {@link Account}.  Will throw {@link IllegalArgumentException} is account
     * name is a duplicate per the current user
     */
    AccountBean create(AccountBean accountBean);

    /**
     * Read a page of {@link AccountBean} for the active user
     * @param pageable
     * @return
     */
    Page<AccountBean> page(Pageable pageable);
}

package com.alimmit.ledger.api.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("from Account a where a.name = ?1 and a.owner = ?#{ authentication?.name }")
    Optional<Account> findByName(@NonNull String name);

    @NonNull
    @Query("from Account a where a.owner = ?#{ authentication?.name }")
    Page<Account> findAll(@NonNull Pageable pageable);
}

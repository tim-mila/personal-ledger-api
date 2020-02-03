package com.alimmit.ledger.api.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Iterable<Transaction> findByTransactionType(TransactionType transactionType);
}

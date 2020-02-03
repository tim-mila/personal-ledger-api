package com.alimmit.ledger.api.transaction;

import com.alimmit.ledger.api.RepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionRepositoryTest extends RepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void contextLoads() {
        assertNotNull(transactionRepository);
    }

    @Test
    void findByTransactionType() {
    }
}
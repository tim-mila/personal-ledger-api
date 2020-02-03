package com.alimmit.ledger.api.account;

import com.alimmit.ledger.api.RepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest extends RepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        final Account account1 = new Account();
        account1.setName("Test Account");
        account1.setDescription("Account repository test account");
        testEntityManager.persist(account1);
    }

    @Test
    void contextLoads() {
        assertNotNull(accountRepository);
    }

    @Test
    void findByName() {
        assertEquals("Test Account", accountRepository.findByName("Test Account").orElseThrow(IllegalStateException::new).getName());
    }

    @Test
    void assertFullCrud() {
        Account account1 = accountRepository.save(Account.create("Foo"));

        assertNotNull(account1);
        assertNotNull(account1.getId(), "Id is null");
        assertNotNull(account1.getCreatedDate(), "CreatedDate is null");
        assertNotNull(account1.getModifiedDate(), "Last modified date is null");

        account1.setDescription("Hello World!");
        account1 = accountRepository.save(account1);
        accountRepository.flush();

        assertEquals("Hello World!", accountRepository.findByName("Foo").orElseThrow(IllegalStateException::new).getDescription(), "Description does not match");
        assertTrue(accountRepository.findByName("Foo").orElseThrow(IllegalStateException::new).getModifiedDate().after(account1.getCreatedDate()), "Last modified not updated");

        accountRepository.deleteById(account1.getId());
        accountRepository.flush();

        assertFalse(accountRepository.findByName("Foo").isPresent());
    }
}
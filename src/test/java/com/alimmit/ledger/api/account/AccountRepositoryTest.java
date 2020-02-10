package com.alimmit.ledger.api.account;

import com.alimmit.ledger.api.ApplicationTests;
import com.alimmit.ledger.api.JwtClaim;
import com.alimmit.ledger.api.JwtHeader;
import com.alimmit.ledger.api.WithMockJwt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest extends /*RepositoryTest*/ ApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

//    @Autowired
//    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        final Account account1 = new Account();
        account1.setName("Test Account");
        account1.setDescription("Account repository test account");
        account1.setOwner("test");
//        testEntityManager.persist(account1);
        accountRepository.save(account1);
    }

    @Test
    void contextLoads() {
        assertNotNull(accountRepository);
    }

    @Test
    @WithMockJwt(tokenValue = "abc123", subject = "test", audiences = { "personal-ledger" }, claims = { @JwtClaim(name = "test_claim", value = "test_claim_value")}, headers = { @JwtHeader(name = "Authorization", value = "Bearer test")})
    void findByName() {
        assertEquals("Test Account", accountRepository.findByName("Test Account").orElseThrow(IllegalStateException::new).getName());
    }

    @Test
    @WithMockJwt(tokenValue = "abc123", subject = "test", audiences = { "personal-ledger" }, claims = { @JwtClaim(name = "test_claim", value = "test_claim_value")}, headers = { @JwtHeader(name = "Authorization", value = "Bearer test")})
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
package com.alimmit.ledger.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {
        "spring.datasource.url = jdbc:tc:postgresql:11.6:///test_database",
        "spring.flyway.sql-migration-prefix = psql-",
        "spring.flyway.sql-migration-separator = __",
        "auth0.audience = test",
        "spring.security.oauth2.resourceserver.jwt.issuer-uri = 'https://test.auth0.com'"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class RepositoryTest {

        @Test
        void contextLoads() {
        }
}

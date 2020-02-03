package com.alimmit.ledger.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {
        "spring.datasource.url = jdbc:tc:postgresql:11.6:///test_database",
        "spring.flyway.sql-migration-prefix = psql-",
        "spring.flyway.sql-migration-separator = __"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({SpringDataConfig.class})
public abstract class RepositoryTest {

        @Test
        void contextLoads() {
        }
}

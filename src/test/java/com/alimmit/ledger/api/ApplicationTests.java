package com.alimmit.ledger.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
		"spring.datasource.url = jdbc:tc:postgresql:11.6:///test_database",
		"spring.flyway.sql-migration-prefix = psql-",
		"spring.flyway.sql-migration-separator = __",
		"auth0.audience = test",
		"spring.security.oauth2.resourceserver.jwt.issuer-uri = https://alimmit.auth0.com/"
})
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}

package com.alimmit.ledger.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url = jdbc:tc:postgresql:11.6:///test_database",
        "spring.flyway.sql-migration-prefix = psql-",
        "spring.flyway.sql-migration-separator = __",
        "auth0.audience = test",
        "spring.security.oauth2.resourceserver.jwt.issuer-uri = https://alimmit.auth0.com/"
})
public abstract class ControllerTest {

    protected static final ObjectMapper MAPPER;
    static {
        MAPPER = new ObjectMapper();
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    protected <T> T parseJson(final Class<T> type, final String json) throws IOException {
        return MAPPER.readValue(json, type);
    }

    @Autowired
    protected MockMvc mockMvc;
}

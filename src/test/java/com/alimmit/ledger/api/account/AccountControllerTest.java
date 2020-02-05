package com.alimmit.ledger.api.account;

import com.alimmit.ledger.api.ControllerTest;
import com.github.dockerjava.core.MediaType;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends ControllerTest {

    @Test
    void create() throws Exception {
        final AccountBean created = parseJson(AccountBean.class,
                mockMvc.perform(
                        post(AccountController.PATH)
                                .content("{\"name\":\"Test\"}")
                                .contentType(MediaType.APPLICATION_JSON.getMediaType())
                                .with(csrf())
                                .with(jwt()))
                        .andExpect(status().is(HttpStatus.CREATED.value()))
                        .andReturn()
                        .getResponse()
                        .getContentAsString());
        Assert.assertNotNull(created);
        Assertions.assertEquals("Test", created.getName());
        Assert.assertNotNull(created.getId());
        Assert.assertNotNull(created.getCreatedDate());
        Assert.assertNotNull(created.getModifiedDate());
    }
}
package com.alimmit.ledger.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collections;

public class WithMockJwtSecurityContextFactory implements WithSecurityContextFactory<WithMockJwt> {

    @Override
    public SecurityContext createSecurityContext(WithMockJwt mockJwt) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Authentication auth = new JwtAuthenticationToken(Jwt.withTokenValue("test").subject("mock").audience(Collections.singletonList("personal-ledger")).claim("test", "test").header("Bearer", "test").build());
        context.setAuthentication(auth);
        return context;
    }
}

package com.alimmit.ledger.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;

public class WithMockJwtSecurityContextFactory implements WithSecurityContextFactory<WithMockJwt> {

    @Override
    public SecurityContext createSecurityContext(WithMockJwt mockJwt) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Jwt.Builder builder = Jwt.withTokenValue(mockJwt.tokenValue())
                .subject(mockJwt.subject())
                .audience(Arrays.asList(mockJwt.audiences()));

        for (JwtClaim claim : mockJwt.claims()) {
            builder.claim(claim.name(), claim.value());
        }

        for (JwtHeader header : mockJwt.headers()) {
            builder.header(header.name(), header.value());
        }

        Authentication auth = new JwtAuthenticationToken(builder.build());

        context.setAuthentication(auth);
        return context;
    }
}

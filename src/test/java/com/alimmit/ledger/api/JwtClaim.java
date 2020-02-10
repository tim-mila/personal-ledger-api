package com.alimmit.ledger.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JwtClaim {

    String name();

    String value();
}

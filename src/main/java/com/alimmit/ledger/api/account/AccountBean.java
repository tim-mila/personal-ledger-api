package com.alimmit.ledger.api.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class AccountBean {

    private final UUID id;
    private final String name;
    private final String description;
    private final Date createdDate;
    private final Date modifiedDate;

    @JsonCreator
    public static AccountBean create(@JsonProperty("name") final String name, @JsonProperty("description") final String description) {
        return new AccountBean(name, description);
    }

    static AccountBean from(final Account account) {
        return new AccountBean(account);
    }

    private AccountBean(final String name, final String description) {
        this.name = name;
        this.description = description;
        this.id = null;
        this.createdDate = null;
        this.modifiedDate = null;
    }

    private AccountBean(final Account account) {
        this.name = account.getName();
        this.description = account.getDescription();
        this.id = account.getId();
        this.createdDate = account.getCreatedDate();
        this.modifiedDate = account.getModifiedDate();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }
}

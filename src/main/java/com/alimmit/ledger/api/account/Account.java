package com.alimmit.ledger.api.account;

import com.alimmit.ledger.api.util.StringUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Account entity is the persistent abstraction for a bank account.
 */
@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
class Account extends AbstractPersistable<UUID> {

    /**
     * Bank account name
     */
    @Column(name =  "name", length = 80, nullable = false)
    private  String name;

    /**
     * Bank account description
     */
    @Column(name =  "description")
    private String description;

    /**
     * System populated, non-modifiable, created date audit field
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    /**
     * System populated last modified date audit field
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date")
    private Date modifiedDate;

    /**
     * System populated created by identifier
     */
    @CreatedBy
    @Column(name = "owner")
    private String owner;

    public static Account create(final String name) {
        return create(name, StringUtils.EMPTY);
    }

    public static Account create(final String name, final String description) {
        final Account account = new Account();
        account.name = name;
        account.description = description;
        return account;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Account{" + "name='" + name + '\'' + "} " + super.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final Account account = (Account) o;
        return name.equals(account.name) &&
                Objects.equals(description, account.description) &&
                createdDate.equals(account.createdDate) &&
                modifiedDate.equals(account.modifiedDate) &&
                owner.equals(account.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, createdDate, modifiedDate, owner);
    }
}

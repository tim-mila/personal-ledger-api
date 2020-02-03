CREATE TABLE account (
    id uuid not null,
    name character varying(80) not null,
    description character varying(255),
    created_date timestamp without time zone not null,
    last_modified_date timestamp without time zone not null,
    constraint pkey_account primary key(id)
);

create index idx_account_name on account (name);
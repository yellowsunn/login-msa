create table users
(
    pk              bigint       not null auto_increment,
    user_id         varchar(255) not null,
    email           varchar(255) not null,
    provider        varchar(50)  not null,
    role            varchar(100) not null,
    thumbnail_image varchar(512),
    created_at      timestamp(6) not null,
    modified_at     timestamp(6) not null,
    primary key (pk),
    unique key uk_user_id (user_id),
    unique key uk_email (email)
);

alter table users
    add index idx_user_id (user_id);

alter table users
    add index idx_email (email);

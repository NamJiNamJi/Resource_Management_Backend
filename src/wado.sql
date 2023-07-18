create sequence user_seq;

create table tb_users (
    user_seq BIGINT not null primary key DEFAULT NEXTVAL('user_seq'),
    user_id VARCHAR(100) not null,
    user_pwd VARCHAR(100) not null,
    user_name VARCHAR(100) not null,
    user_email VARCHAR(100) not null,
    user_created TIMESTAMP not null,
    user_updated TIMESTAMP not null
);
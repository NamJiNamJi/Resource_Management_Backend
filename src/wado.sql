/* 유저 테이블 생성 */
create table tb_users (
                          user_seq SERIAL primary key,
                          user_id VARCHAR(100) not null,
                          user_pwd VARCHAR(100) not null,
                          user_name VARCHAR(100) not null,
                          user_email VARCHAR(100) not null,
                          user_image varchar(255) not null,
                          user_created TIMESTAMP default now(),
                          user_updated TIMESTAMP default now()
);
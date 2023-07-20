/* 유저 테이블 생성 */
create table tb_users (
                          user_seq SERIAL primary key,
                          user_id VARCHAR(100) not null,
                          user_pwd VARCHAR(100) not null,
                          user_name VARCHAR(100) not null,
                          user_email VARCHAR(100) not null,
                          user_created TIMESTAMP not null,
                          user_updated TIMESTAMP not null
);
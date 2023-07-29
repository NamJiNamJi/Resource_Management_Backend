/* 유저 테이블 생성 */
create table tb_users (
                          user_seq SERIAL primary key,
                          user_id VARCHAR(100) not null,
                          user_pwd VARCHAR(100) not null,
                          user_name VARCHAR(100) not null,
                          user_email VARCHAR(100) not null,
<<<<<<< HEAD
                          user_image varchar(255) not null,
                          user_created TIMESTAMP default now(),
                          user_updated TIMESTAMP default now()
=======
                          user_created TIMESTAMP not null,
                          user_updated TIMESTAMP not null
);

/* 차량 자원 테이블 생성 */
CREATE TABLE tb_cars
(
    car_seq      SERIAL primary key,
    car_name     VARCHAR(100) not null,
    car_number   VARCHAR(100) not null,
    car_distance VARCHAR(100) not null,
    car_year     TIMESTAMP    not null,
    car_image    VARCHAR(255) null,
    car_explain  VARCHAR(255) null,
    car_state    BOOLEAN      default true,
    cop_seq      INTEGER      not null,
    rsc_seq      INTEGER      not null,
    car_created  TIMESTAMP    default now(),
    car_updated  TIMESTAMP    default now(),
);

/* 전자기기 자원 테이블 생성 */
CREATE TABLE tb_devices
(
    dvc_seq     SERIAL primary key,
    dvc_name    VARCHAR(100) not null,
    dvc_serial  VARCHAR(255) not null,
    dvc_image   VARCHAR(255) null,
    dvc_explain VARCHAR(255) null,
    dvc_buy     TIMESTAMP    not null,
    dvc_created TIMESTAMP    not null,
    dvc_updated TIMESTAMP    not null,
    dvc_state   BOOLEAN      not null,
    cop_seq     INTEGER      not null,
    rsc_seq     INTEGER      not null
);

/* 공간 자원 테이블 생성 */
CREATE TABLE tb_spaces
(
    spc_seq     SERIAL primary key,
    spc_name    VARCHAR(100) not null,
    spc_cap     INTEGER      not null,
    spc_explain VARCHAR(255) null,
    spc_image   VARCHAR(255) null,
    spc_created TIMESTAMP    not null,
    spc_updated TIMESTAMP    not null,
    spc_state   BOOLEAN      not null,
    cop_seq     INTEGER      not null,
    rsc_seq     INTEGER      not null
>>>>>>> a3ad25de1b3fbdc16434e4a2039a62315a382a22
);
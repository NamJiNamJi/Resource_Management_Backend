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
);

/* 회사 테이블 생성 */
CREATE TABLE tb_companys(
    cop_seq SERIAL PRIMARY KEY,
    cop_reg_num VARCHAR(100) NOT NULL,
    cop_name VARCHAR(100) NOT NULL,
    cop_state BOOLEAN DEFAULT TRUE,
    cop_created TIMESTAMP DEFAULT now(),
    cop_updated TIMESTAMP DEFAULT now()
);

/* 사원 테이블 생성 */
CREATE TABLE tb_employees(
     emp_seq SERIAL PRIMARY KEY,
     emp_name VARCHAR(100) NOT NULL,
     emp_position VARCHAR(100),
     emp_image VARCHAR(255),
     cop_seq INTEGER NOT NULL,
     user_seq INTEGER NOT NULL,
     auth_level VARCHAR(100),
     emp_state BOOLEAN DEFAULT TRUE,
     emp_created TIMESTAMP DEFAULT now(),
     emp_updated TIMESTAMP DEFAULT now()
);

/* 테스트 테이블 생성 */
CREATE TABLE tb_tests(
     test_seq SERIAL PRIMARY KEY,
     test_data1 VARCHAR(100) NOT NULL,
     test_data2 VARCHAR(100) NOT NULL,
     test_data3 VARCHAR(100) NOT NULL,
     imgUrl VARCHAR(255) NOT NULL,
     test_created TIMESTAMP DEFAULT now(),
     test_updated TIMESTAMP DEFAULT now()

);
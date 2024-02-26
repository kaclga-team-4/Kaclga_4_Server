CREATE TABLE profile
(
    id               BIGINT AUTO_INCREMENT          NOT NULL,
    updated_at       datetime                       NOT NULL,
    created_at       datetime                       NOT NULL,
    profile_name     VARCHAR(30)  default 'default' NOT NULL,
    origin_file_name VARCHAR(255) default 'default' NOT NULL,
    CONSTRAINT pk_profile PRIMARY KEY (id)
);

CREATE TABLE member
(
    id           BIGINT AUTO_INCREMENT                                NOT NULL,
    created_at   datetime                                             NOT NULL,
    updated_at   datetime                                             NOT NULL,
    member_name  VARCHAR(90)                                          NOT NULL,
    email        VARCHAR(100)                                         NULL,
    social_type  enum ('GOOGLE', 'EMAIL')                             NOT NULL,
    member_role  enum ('ROLE_USER', 'ROLE_ADMIN') default 'ROLE_USER' NOT NULL,
    notice_check enum ('UNCHECKED','CHECKED')     default 'CHECKED'   NOT NULL,
    nickname     VARCHAR(24)                                          NOT NULL,
    phone_number VARCHAR(13)                                          NULL,
    gender       enum ('M','F')                                       NULL,
    birthday     date                                                 NULL,
    profile_id   BIGINT                                               NOT NULL,
    CONSTRAINT pk_member PRIMARY KEY (id)
);

ALTER TABLE member
    ADD CONSTRAINT uc_member_email UNIQUE (email);

ALTER TABLE member
    ADD CONSTRAINT uc_member_member_name UNIQUE (member_name);

ALTER TABLE member
    ADD CONSTRAINT uc_member_nickname UNIQUE (nickname);

ALTER TABLE member
    ADD CONSTRAINT uc_member_phone_number UNIQUE (phone_number);

ALTER TABLE member
    ADD CONSTRAINT uc_member_profile UNIQUE (profile_id);

ALTER TABLE member
    ADD CONSTRAINT FK_MEMBER_ON_PROFILE FOREIGN KEY (profile_id) REFERENCES profile (id);

CREATE INDEX profile_id_index ON member (profile_id);

create table test_entity
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table test_image
(
    image_id    bigint auto_increment
        primary key,
    origin_name varchar(100) null,
    stored_name varchar(100) null,
    access_url  varchar(100) null
);

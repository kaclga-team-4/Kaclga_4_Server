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



create table bookmark (
                          created_at datetime(6),
                          id bigint not null auto_increment,
                          job_posting_id bigint,
                          member_id bigint,
                          primary key (id)
);

create table career (
                        id bigint not null auto_increment,
                        type varchar(255),
                        primary key (id)
);

create table education (
                           id bigint not null auto_increment,
                           type varchar(255),
                           primary key (id)
);

create table job (
                     id bigint not null auto_increment,
                     type varchar(255),
                     primary key (id)
);

create table job_category (
                              id bigint not null auto_increment,
                              type varchar(255),
                              primary key (id)
);

create table job_detail (
                            id bigint not null auto_increment,
                            job_category_id bigint,
                            job_id bigint,
                            type varchar(255),
                            primary key (id)
);

create table job_posting (
                             created_at date,
                             deadline date,
                             career_id bigint,
                             education_id bigint,
                             id bigint not null auto_increment,
                             job_id bigint,
                             company_name varchar(255),
                             post_name varchar(255),
                             url varchar(255),
                             primary key (id)
);

create table job_posting_work_type (
                                       id bigint not null auto_increment,
                                       job_postign bigint,
                                       job_posting_id bigint,
                                       primary key (id)
);


create table notification (
                              created_at date,
                              id bigint not null auto_increment,
                              join_posting_id bigint,
                              member_id bigint,
                              contents varchar(255),
                              primary key (id)
);

create table preference_job (
                                id bigint not null auto_increment,
                                job_id bigint,
                                member_id bigint,
                                primary key (id)
);


create table region_1st (
                            id bigint not null auto_increment,
                            type varchar(255),
                            primary key (id)
);

create table region_2nd (
                            id bigint not null auto_increment,
                            region_1st_id bigint,
                            type varchar(255),
                            primary key (id)
);

create table work_type (
                           id bigint not null auto_increment,
                           type varchar(255),
                           primary key (id)
);

create index career_idx_type
    on career (type);

alter table career
    add constraint career_uk unique (type);

create index education_idx_type
    on education (type);

alter table education
    add constraint education_uk unique (type);

create index job_idx_type
    on job (type);

alter table job
    add constraint job_uk unique (type);

create index job_category_idx_type
    on job_category (type);

alter table job_category
    add constraint job_category_uk unique (type);

create index job_detail_idx_type
    on job_detail (type);

alter table job_detail
    add constraint job_detail_uk unique (type, job_id);

alter table member
    add constraint UK_52psa23ioy6c74qam4i7p2aen unique (profile_id);

create index region_1st_idx_type
    on region_1st (type);

alter table region_1st
    add constraint region_1st_uk unique (type);

create index region_2st_idx_type
    on region_2nd (type)

alter table region_2nd
    add constraint region_2st_uk unique (type)

create index idx_type
    on work_type (type)

alter table work_type
    add constraint worktype_uk unique (type)

alter table bookmark
    add constraint fk_bookmark_job_posting_id
        foreign key (job_posting_id)
            references job_posting (id)

alter table bookmark
    add constraint fk_bookmark_member_id
        foreign key (member_id)
            references member (id)

alter table job_detail
    add constraint fk_job_detail_job_id
        foreign key (job_id)
            references job (id)

alter table job_detail
    add constraint fk_job_detail_job_category_id
        foreign key (job_category_id)
            references job_category (id)

alter table job_posting
    add constraint fk_job_posting_career_id
        foreign key (career_id)
            references career (id)

alter table job_posting
    add constraint fk_job_posting_education_id
        foreign key (education_id)
            references education (id)

alter table job_posting
    add constraint fk_job_posting_job_id
        foreign key (job_id)
            references job (id)

alter table job_posting_work_type
    add constraint fk_job_posting_work_type_job_posting_id
        foreign key (job_posting_id)
            references job_posting (id)

alter table job_posting_work_type
    add constraint fk_job_posting_work_type_work_type_id
        foreign key (job_postign)
            references work_type (id)

alter table member
    add constraint fk_member_profile_id
        foreign key (profile_id)
            references profile (id)

alter table notification
    add constraint notification_job_posting_id
        foreign key (join_posting_id)
            references job_posting (id)

alter table notification
    add constraint notification_member_id
        foreign key (member_id)
            references member (id)

alter table preference_job
    add constraint preference_job_id
        foreign key (job_id)
            references job (id)

alter table preference_job
    add constraint preference_member_id
        foreign key (member_id)
            references member (id);

alter table region_2nd
    add constraint fk_region_2nd
        foreign key (region_1st_id)
            references region_1st (id)
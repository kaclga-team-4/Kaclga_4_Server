CREATE TABLE profile
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    updated_at datetime              NOT NULL,
    created_at datetime              NOT NULL,
    url        VARCHAR(255)          NOT NULL,
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

CREATE TABLE education
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    type VARCHAR(255)          NULL,
    CONSTRAINT pk_education PRIMARY KEY (id)
);

ALTER TABLE education
    ADD CONSTRAINT education_uk UNIQUE (type);

CREATE INDEX education_idx_type ON education (type);

CREATE TABLE job_posting
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    company_name VARCHAR(255)          NULL,
    post_name    VARCHAR(255)          NULL,
    education_id BIGINT                NULL,
    url          VARCHAR(255)          NULL,
    deadline     DATE                  NULL,
    created_at   DATE                  NULL,
    CONSTRAINT pk_jobposting PRIMARY KEY (id)
);

ALTER TABLE job_posting
    ADD CONSTRAINT job_posting_company_name_post_name_uk UNIQUE (company_name, post_name);

ALTER TABLE job_posting
    ADD CONSTRAINT FK_JOB_POSTING_EDUCATION_ID FOREIGN KEY (education_id) REFERENCES education (id);

CREATE TABLE bookmark
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    member_id      BIGINT                NULL,
    job_posting_id BIGINT                NULL,
    CONSTRAINT pk_bookmark PRIMARY KEY (id)
);

ALTER TABLE bookmark
    ADD CONSTRAINT bookmark_member_id_job_posting_id_unique UNIQUE (member_id, job_posting_id);

ALTER TABLE bookmark
    ADD CONSTRAINT FK_BOOKMARK_JOB_POSTING_ID FOREIGN KEY (job_posting_id) REFERENCES job_posting (id);

ALTER TABLE bookmark
    ADD CONSTRAINT FK_BOOKMARK_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member (id);

CREATE TABLE career
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    type VARCHAR(255)          NULL,
    CONSTRAINT pk_career PRIMARY KEY (id)
);

ALTER TABLE career
    ADD CONSTRAINT career_uk UNIQUE (type);

CREATE INDEX career_idx_type ON career (type);


CREATE TABLE job
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    type VARCHAR(255)          NULL,
    CONSTRAINT pk_job PRIMARY KEY (id)
);

ALTER TABLE job
    ADD CONSTRAINT job_uk UNIQUE (type);

CREATE INDEX job_idx_type ON job (type);

CREATE TABLE job_category
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    type VARCHAR(255)          NULL,
    CONSTRAINT pk_job_category PRIMARY KEY (id)
);

ALTER TABLE job_category
    ADD CONSTRAINT job_category_uk UNIQUE (type);

CREATE INDEX job_category_idx_type ON job_category (type);


CREATE TABLE job_detail
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    type            VARCHAR(255)          NULL,
    job_id          BIGINT                NULL,
    job_category_id BIGINT                NULL,
    CONSTRAINT pk_job_detail PRIMARY KEY (id)
);

ALTER TABLE job_detail
    ADD CONSTRAINT job_detail_uk UNIQUE (type, job_id);

CREATE INDEX job_detail_idx_type ON job_detail (type);

ALTER TABLE job_detail
    ADD CONSTRAINT FK_JOB_DETAIL_JOB_CATEGORY_ID FOREIGN KEY (job_category_id) REFERENCES job_category (id);

ALTER TABLE job_detail
    ADD CONSTRAINT FK_JOB_DETAIL_JOB_ID FOREIGN KEY (job_id) REFERENCES job (id);

CREATE TABLE job_detail_posting_relation
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    job_posting_id BIGINT                NULL,
    job_detail_id  BIGINT                NULL,
    CONSTRAINT pk_jobdetailpostingrelation PRIMARY KEY (id)
);

ALTER TABLE job_detail_posting_relation
    ADD CONSTRAINT job_detail_posting_relation_job_posting_id_job_detail_id_unique UNIQUE (job_posting_id, job_detail_id);

ALTER TABLE job_detail_posting_relation
    ADD CONSTRAINT FK_JOB_DETAIL_POSTING_RELATION_JOB_DETAIL_ID FOREIGN KEY (job_detail_id) REFERENCES job_detail (id);

ALTER TABLE job_detail_posting_relation
    ADD CONSTRAINT FK_JOB_DETAIL_POSTING_RELATION_JOB_POSTING_ID FOREIGN KEY (job_posting_id) REFERENCES job_posting (id);

CREATE TABLE job_posting_career
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    job_posting_id BIGINT                NULL,
    career_id      BIGINT                NULL,
    CONSTRAINT pk_job_posting_career PRIMARY KEY (id)
);

ALTER TABLE job_posting_career
    ADD CONSTRAINT job_posting_career_job_posting_id_career_id_unique UNIQUE (job_posting_id, career_id);

ALTER TABLE job_posting_career
    ADD CONSTRAINT JOB_POSTING_CAREER_CAREER_ID FOREIGN KEY (career_id) REFERENCES career (id);

ALTER TABLE job_posting_career
    ADD CONSTRAINT JOB_POSTING_CAREER_JOB_POSTING_ID FOREIGN KEY (job_posting_id) REFERENCES job_posting (id);

CREATE TABLE work_type
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    type VARCHAR(255)          NULL,
    CONSTRAINT pk_work_type PRIMARY KEY (id)
);

ALTER TABLE work_type
    ADD CONSTRAINT worktype_uk UNIQUE (type);

CREATE INDEX idx_type ON work_type (type);

CREATE TABLE job_posting_work_type
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    job_posting_id BIGINT                NULL,
    work_type_id   BIGINT                NULL,
    CONSTRAINT pk_jobpostingworktype PRIMARY KEY (id)
);

ALTER TABLE job_posting_work_type
    ADD CONSTRAINT job_posting_work_type_job_posting_id_work_type_id_unique UNIQUE (job_posting_id, work_type_id);

ALTER TABLE job_posting_work_type
    ADD CONSTRAINT FK_JOB_POSTING_WORK_TYPE_JOB_POSTING_ID FOREIGN KEY (job_posting_id) REFERENCES job_posting (id);

ALTER TABLE job_posting_work_type
    ADD CONSTRAINT FK_JOB_POSTING_WORK_TYPE_WORK_TYPE_ID FOREIGN KEY (work_type_id) REFERENCES work_type (id);

CREATE TABLE notification
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime              NOT NULL,
    contents        VARCHAR(255)          NULL,
    member_id       BIGINT                NULL,
    join_posting_id BIGINT                NULL,
    CONSTRAINT pk_notification PRIMARY KEY (id)
);

ALTER TABLE notification
    ADD CONSTRAINT notification_member_id_job_posting_id_unique UNIQUE (member_id, join_posting_id);

ALTER TABLE notification
    ADD CONSTRAINT NOTIFICATION_JOB_POSTING_ID FOREIGN KEY (join_posting_id) REFERENCES job_posting (id);

ALTER TABLE notification
    ADD CONSTRAINT NOTIFICATION_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member (id);

CREATE TABLE preference_job
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    member_id     BIGINT                NULL,
    job_detail_id BIGINT                NULL,
    CONSTRAINT pk_preference_job PRIMARY KEY (id)
);

ALTER TABLE preference_job
    ADD CONSTRAINT preference_job_member_id_job_detail_id_unique UNIQUE (member_id, job_detail_id);

ALTER TABLE preference_job
    ADD CONSTRAINT FK_PREFERENCE_JOB_DETAIL_ID FOREIGN KEY (job_detail_id) REFERENCES job_detail (id);

ALTER TABLE preference_job
    ADD CONSTRAINT FK_PREFERENCE_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member (id);

CREATE TABLE region_1st
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    type VARCHAR(255)          NULL,
    CONSTRAINT pk_region_1st PRIMARY KEY (id)
);

ALTER TABLE region_1st
    ADD CONSTRAINT region_1st_uk UNIQUE (type);

CREATE INDEX region_1st_idx_type ON region_1st (type);

CREATE TABLE region_2nd
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    type          VARCHAR(255)          NULL,
    region_1st_id BIGINT                NULL,
    CONSTRAINT pk_region_2nd PRIMARY KEY (id)
);

ALTER TABLE region_2nd
    ADD CONSTRAINT FK_REGION_2ND FOREIGN KEY (region_1st_id) REFERENCES region_1st (id);

CREATE TABLE region_posting_relation
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    job_posting_id BIGINT                NULL,
    region_2nd_id  BIGINT                NULL,
    CONSTRAINT pk_region_posting_relation PRIMARY KEY (id)
);

ALTER TABLE region_posting_relation
    ADD CONSTRAINT region_posting_relation_job_posting_id_region_2nd_id_unique UNIQUE (job_posting_id, region_2nd_id);

ALTER TABLE region_posting_relation
    ADD CONSTRAINT FK_REGION_POSTING_RELATION_JOB_POSTING_ID FOREIGN KEY (job_posting_id) REFERENCES job_posting (id);

ALTER TABLE region_posting_relation
    ADD CONSTRAINT FK_REGION_POSTING_RELATION_REGION_2ND_ID FOREIGN KEY (region_2nd_id) REFERENCES region_2nd (id);

CREATE TABLE career_member_relation
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    member_id BIGINT                NULL,
    career_id BIGINT                NULL,
    CONSTRAINT pk_career_member_relation PRIMARY KEY (id)
);

ALTER TABLE career_member_relation
    ADD CONSTRAINT FK_CAREER_MEMBER_RELATION_CAREER_ID FOREIGN KEY (career_id) REFERENCES career (id);

ALTER TABLE career_member_relation
    ADD CONSTRAINT FK_CAREER_MEMBER_RELATION_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member (id);

CREATE TABLE education_member_relation
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    member_id    BIGINT                NULL,
    education_id BIGINT                NULL,
    CONSTRAINT pk_education_member_relation PRIMARY KEY (id)
);

ALTER TABLE education_member_relation
    ADD CONSTRAINT FK_EDUCATION_MEMBER_RELATION_ON_EDUCATION FOREIGN KEY (education_id) REFERENCES education (id);

ALTER TABLE education_member_relation
    ADD CONSTRAINT FK_EDUCATION_MEMBER_RELATION_ON_MEMBER FOREIGN KEY (member_id) REFERENCES member (id);

CREATE TABLE region_member_relatoin
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    member_id     BIGINT                NULL,
    region_2nd_id BIGINT                NULL,
    CONSTRAINT pk_region_member_relatoin PRIMARY KEY (id)
);

ALTER TABLE region_member_relatoin
    ADD CONSTRAINT FK_REGION_MEMBER_RELATION_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member (id);

ALTER TABLE region_member_relatoin
    ADD CONSTRAINT FK_REGION_MEMBER_RELATION_REGION_2ND_ID FOREIGN KEY (region_2nd_id) REFERENCES region_2nd (id);

CREATE TABLE work_type_member_relation
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    member_id    BIGINT                NULL,
    work_type_id BIGINT                NULL,
    CONSTRAINT pk_work_type_member_relation PRIMARY KEY (id)
);

ALTER TABLE work_type_member_relation
    ADD CONSTRAINT FK_WORK_TYPE_MEMBER_RELATION_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member (id);

ALTER TABLE work_type_member_relation
    ADD CONSTRAINT FK_WORK_TYPE_MEMBER_RELATION_WORK_TYPE_ID FOREIGN KEY (work_type_id) REFERENCES work_type (id);

CREATE TABLE last_job_posting_id
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    last_job_posting_id BIGINT                NOT NULL,
    CONSTRAINT pk_last_job_posting_id PRIMARY KEY (id)
);

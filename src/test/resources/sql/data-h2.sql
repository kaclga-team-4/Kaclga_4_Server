insert into test_entity
values (100, 'test');
insert into career
values (1, '신입');
insert into career
values (2, '경력');
insert into education
values (1, '고졸');
insert into education
values (2, '대졸');
insert into job
values (1, 'IT개발·데이터');
insert into job_category
values (1, '직무·직업');
insert into job_detail
values (1, '웹개발', 1, 1);
insert into job_detail
values (2, '프론트웹개발', 1, 1);
insert into region_1st
values (1, '서울');
insert into region_2nd
values (1, '강남구', 1);
insert into work_type
values (1, '정규직');
insert into work_type
values (2, '계약직');
insert into job_posting(id, company_name, post_name, education_id, url, deadline, created_at)
values (1, 'Google', 'I want Software Engineer', 1, 'url2313', '2023-03-12', '2023-03-12');
insert into job_posting_career
values (1, 1, 1);
insert into region_posting_relation
values (1, 1, 1);
insert into job_posting_work_type
values (1, 1, 1);
insert into job_detail_posting_relation
values (1, 1, 1);

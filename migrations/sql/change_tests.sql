alter table tests rename column test_id to question_id;
alter table tests rename column course_id to test_id;
alter table tests alter column options type text;
alter table tests rename to questions;



create table if not exists tests(
    test_id bigint generated always as identity,
    course_id bigint not null
);
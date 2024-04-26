alter table tests rename column course_id to answer;
alter table tests alter column answer type varchar;

alter table questions rename column test_id to course_id;

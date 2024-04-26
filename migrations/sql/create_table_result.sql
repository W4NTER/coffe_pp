create table if not exists test_res(
    test_res_id bigint generated always as identity,
    user_id bigint not null,
    course_id bigint not null,
    result bigint not null
);
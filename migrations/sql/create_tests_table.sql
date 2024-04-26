create table if not exists tests(
    test_id bigint generated always as identity,
    course_id bigint not null,
    question varchar not null,
    correct_answer varchar not null,
    options json not null
);
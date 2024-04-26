create table if not exists courses(
    course_id bigint generated always as identity,
    course_title varchar(50) not null,
    description varchar(100) not null,
    start_date timestamp with time zone,
    end_date timestamp with time zone
);

create table if not exists courses_to_users(
    course_id bigint not null,
    user_id bigint not null
);

create table if not exists module(
    module_id bigint not null,
    module_title varchar(50) not null,
    course_id bigint not null,
    start_date timestamp with time zone,
    end_date timestamp with time zone
);

create table if not exists content(
    content_title varchar(50),
    description varchar(50),
    image_path varchar(50),
    body varchar,
    module_id bigint not null
);

create table if not exists user_module_progress(
    progress_id bigint generated always as identity,
    user_id bigint not null,
    module_id bigint not null,
    completed boolean default false
);


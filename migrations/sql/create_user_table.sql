create table if not exists users (
    user_id bigint generated always as identity,
    email varchar(50) not null,
    password varchar(50) not null,
    active boolean not null,

    primary key(user_id)
);
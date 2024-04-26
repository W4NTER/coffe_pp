create table if not exists documents (
    document_id bigint generated always as identity,
    user_id bigint not null,
    fileName varchar(50) not null
);
create table if not exists users(
    id bigserial primary key,
    username varchar(30) not null unique,
    email varchar(50) not null unique,
    password varchar not null,
    status varchar(15) not null,
    createdAt date not null
);

create table IF NOT EXISTS roles(
    id bigserial primary key,
    title varchar unique not null
);
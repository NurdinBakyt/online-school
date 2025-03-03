create table if not exists school(
    id serial primary key,
    name varchar(100) not null
    school_id bigint not null,
    foreign key (school_id) references school(id)
)

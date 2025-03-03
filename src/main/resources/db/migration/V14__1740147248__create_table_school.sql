create table if not exists school(
    id serial primary key,
    name varchar(100) not null
    city_id bigint not null,
    foreign key (city_id) references city(id)
)

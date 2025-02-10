create table if not exists bids_for_work(
    id bigserial primary key ,
    info_of_employee varchar not null,
    documents_of_employee varchar not null,
    bid_status varchar(50) not null,
    email varchar not null
);
create table if not exists invitation_to_interview_for_work (
    id bigserial primary key ,
    invitation varchar,
    interview_date varchar,
    email_user varchar
);

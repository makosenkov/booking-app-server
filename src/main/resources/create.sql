create table room
(
    id     bigserial primary key,
    name   text not null
);

create table booking
(
    id     bigserial primary key,
    room_id bigint references room,
    start_date timestamp not null,
    end_date timestamp not null
);
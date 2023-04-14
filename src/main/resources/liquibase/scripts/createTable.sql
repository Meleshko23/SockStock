-- liquebase formatted sql
-- changeset meleshko:1

create table socks

(
    id          Serial primary key,
    color       varchar not null,
    cotton_part integer not null,
    quantity    integer not null
);
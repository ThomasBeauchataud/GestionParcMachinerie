create table bill
(
    id        int auto_increment
        primary key,
    client_id int                  null,
    commands  varchar(255)         null,
    paid      tinyint(1) default 0 null,
    value     int                  null
);
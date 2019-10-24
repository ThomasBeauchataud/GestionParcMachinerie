create table command
(
    id                  int auto_increment primary key,
    client_id           int,
    machine_id          int,
    `from`              long,
    `to`                long
);

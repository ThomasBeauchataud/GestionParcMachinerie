create table machine
(
    id                  int auto_increment primary key,
    user_id             int,
    machine_id          int,
    `from`              long,
    `to`                long
);

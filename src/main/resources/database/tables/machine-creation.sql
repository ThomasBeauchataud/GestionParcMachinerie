create table machine
(
    id                  int auto_increment primary key,
    family              varchar(255)  not null,
    model               varchar(255) not null,
    rentPrice           int not null
);

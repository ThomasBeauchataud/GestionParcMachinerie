create table machine
(
    id                  int auto_increment primary key,
    family              varchar(255),
    model               varchar(255),
    rentPrice           int,
    status              varchar(255)
);

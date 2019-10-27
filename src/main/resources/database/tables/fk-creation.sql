alter table command
    add constraint command_machine_id_fk
        foreign key (machine_id) references machine (id);
alter table command
    add constraint command_client_id_fk
        foreign key (client_id) references client (id);
alter table bill
    add constraint bill_client_id_fk
        foreign key (client_id) references client (id)


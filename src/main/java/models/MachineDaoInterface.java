package models;

import beans.entities.Machine;
import models.common.CommonDaoInterface;

public interface MachineDaoInterface extends CommonDaoInterface<Machine> {
    Machine getByName(String name);
    Machine getById(int id);

    void deleteByName(String name);
    void deleteById(int id);
}

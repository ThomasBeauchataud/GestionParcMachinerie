package models;

import beans.entities.Machine;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@ApplicationScoped
@Default
public class MachineDao extends CommonDao implements MachineDaoInterface {

    public void insert(Machine object) {

    }

    public void update(Machine object) {

    }

    public Machine getById(int id) {
        return null;
    }

    public Machine getByName(String name) {
        return null;
    }

    public void deleteById(int id) {

    }

    public void deleteByName(String name) {

    }

}

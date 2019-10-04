package models;

import beans.Machine;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@ApplicationScoped
@Default
public class MachineDao extends CommonDao implements MachineDaoInterface {

    @Override
    public void insert(Machine object) {

    }

    @Override
    public void update(Machine object) {

    }

    @Override
    public Machine getById(int id) {
        return null;
    }

    @Override
    public Machine getByName(String name) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByName(String name) {

    }

}

package models;

import beans.entities.Client;
import models.common.CommonDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao extends CommonDao<Client> implements ClientDaoInterface {

    @Override
    public void insert(Client object) {

    }

    @Override
    public void update(Client object) {

    }

    @Override
    public Client getById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    protected Client generateEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

}

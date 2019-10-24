package models;

import beans.entities.Client;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
@Default
public class ClientDao extends CommonDao<Client> implements ClientDaoInterface {

    @Override
    public void insert(Client object) {

    }

    @Override
    public void update(Client object) {

    }

    @Override
    public Client getById(int id) {
        return super.getById(selectById, id);
    }

    @Override
    public Client[] getAll() {
        return super.getAll(select).toArray(new Client[0]);
    }

    @Override
    public Client getByName(String name) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(deleteById, id);
    }

    @Override
    protected Client generateEntity(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("email")
        );
    }

    private static final String select = "SELECT * FROM client";
    private static final String selectById = "SELECT * FROM client WHERE id = ?";
    private static final String deleteById = "DELETE FROM client WHERE id = ?";

}

package models;

import beans.entities.Client;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
@Default
@SuppressWarnings("FieldCanBeLocal")
public class ClientDao extends CommonDao<Client> implements ClientDaoInterface {

    @Override
    public void insert(Client client) {
        super.insert(insert, new Object[]{client.getName(), client.getSurname(), client.getEmail()});
    }

    @Override
    public void update(Client client) {
        super.update(update, new Object[]{client.getEmail(), client.getId()});
    }

    @Override
    public Client getById(int id) {
        return super.getById(selectById, id);
    }

    @Override
    public List<Client> getAll() {
        return super.getAll(select);
    }

    @Override
    public Client getByEmail(String email) {
        return super.getOne(selectByEmail, new Object[]{email});
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
    private static final String selectByEmail = "SELECT * FROM client WHERE email = ?";
    private static final String deleteById = "DELETE FROM client WHERE id = ?";
    private static String insert = "INSERT INTO client (name, surname, email) VALUES (?, ?, ?)";
    private static String update = "UPDATE client SET email = ? WHERE id = ?";

}

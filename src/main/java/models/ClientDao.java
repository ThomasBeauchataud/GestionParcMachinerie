package models;

import beans.entities.Client;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
@Default
@SuppressWarnings("FieldCanBeLocal")
public class ClientDao extends CommonDao<Client> implements ClientDaoInterface {

    @Override
    public void insert(Client client) {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.execute();
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    @Override
    public void update(Client client) {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(update);
            preparedStatement.setString(1, client.getEmail());
            preparedStatement.setInt(2, client.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            log(e.getMessage());
        }
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
    public Client getByEmail(String email) {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(selectByEmail);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return this.generateEntity(resultSet);
        } catch (Exception e) {
            log(e.getMessage());
            return null;
        }
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

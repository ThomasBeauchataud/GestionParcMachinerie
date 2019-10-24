package models;

import beans.entities.Machine;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
@Default
@SuppressWarnings("FieldCanBeLocal")
public class MachineDao extends CommonDao<Machine> implements MachineDaoInterface {

    @Override
    public void insert(Machine machine) {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, machine.getFamily());
            preparedStatement.setString(2, machine.getModel());
            preparedStatement.setInt(3, machine.getRentPrice());
            preparedStatement.execute();
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    @Override
    public void update(Machine machine) {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(update);
            preparedStatement.setInt(1, machine.getRentPrice());
            preparedStatement.setInt(2, machine.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    @Override
    public Machine getById(int id) {
        return super.getById(selectById, id);
    }

    @Override
    public Machine[] getAll() {
        return super.getAll(select).toArray(new Machine[0]);
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(deleteById, id);
    }

    @Override
    protected Machine generateEntity(ResultSet resultSet) throws SQLException {
        return new Machine(
                resultSet.getInt("id"),
                resultSet.getString("family"),
                resultSet.getString("model"),
                resultSet.getInt("rentPrice")
        );
    }

    private static String insert = "INSERT INTO machine (family, model, rentPrice) VALUES (?, ?, ?)";
    private static String update = "UPDATE machine SET rentPrice = ? WHERE id = ?";
    private static String select = "SELECT * FROM machine";
    private static String selectById = "SELECT * FROM machine WHERE id = ?";
    private static String deleteById = "DELETE FROM machine WHERE id = ?";


}

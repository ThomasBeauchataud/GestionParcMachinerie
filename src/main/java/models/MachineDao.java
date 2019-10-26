package models;

import beans.entities.Machine;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
@Default
@SuppressWarnings("FieldCanBeLocal")
public class MachineDao extends CommonDao<Machine> implements MachineDaoInterface {

    @Override
    public void insert(Machine machine) {
        super.insert(insert, new Object[]{machine.getModel(), machine.getRentPrice()});
    }

    @Override
    public void update(Machine machine) {
        super.update(update, new Object[]{machine.getRentPrice(), machine.getId()});
    }

    @Override
    public Machine getById(int id) {
        return super.getById(selectById, id);
    }

    @Override
    public List<Machine> getAll() {
        return super.getAll(select);
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(deleteById, id);
    }

    @Override
    protected Machine generateEntity(ResultSet resultSet) throws SQLException {
        return new Machine(
                resultSet.getInt("id"),
                resultSet.getString("model"),
                resultSet.getInt("rentPrice")
        );
    }

    private static String insert = "INSERT INTO machine (model, rentPrice) VALUES (?, ?)";
    private static String update = "UPDATE machine SET rentPrice = ? WHERE id = ?";
    private static String select = "SELECT * FROM machine";
    private static String selectById = "SELECT * FROM machine WHERE id = ?";
    private static String deleteById = "DELETE FROM machine WHERE id = ?";


}

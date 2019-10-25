package models;

import beans.entities.Client;
import beans.entities.Command;
import beans.entities.Machine;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Default
@ApplicationScoped
@SuppressWarnings("FieldCanBeLocal")
public class CommandDao extends CommonDao<Command> implements CommandDaoInterface {

    @Override
    public void insert(Command object) {

    }

    @Override
    public void update(Command object) {

    }

    @Override
    public Command getById(int id) {
        return super.getById(selectById, id);
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(deleteById, id);
    }

    @Override
    public Command[] getAll() {
        return super.getAll(select).toArray(new Command[0]);
    }

    @Override
    public List<Command> getFuture() {
        List<Command> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(selectUpFrom);
            preparedStatement.setLong(1, new Date().getTime());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            list.add(this.generateEntity(resultSet));
        } catch (Exception e) {
            log(e.getMessage());
        }
        return list;
    }

    @Override
    protected Command generateEntity(ResultSet resultSet) throws SQLException {
        return new Command(
                resultSet.getInt("id"),
                new Machine(resultSet.getInt("machine_id")),
                new Client(resultSet.getInt("client_id")),
                new Date(resultSet.getLong("from")),
                new Date(resultSet.getLong("to"))
        );
    }

    private static final String select = "SELECT * FROM command";
    private static final String selectById = "SELECT * FROM command WHERE id = ?";
    private static final String selectUpFrom = "SELECT * FROM command WHERE `from` > ?";
    private static final String deleteById = "DELETE FROM command WHERE id = ?";

}

package models;

import beans.entities.Client;
import beans.entities.Command;
import beans.entities.Machine;
import beans.entities.enums.CommandStatus;
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
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, object.getClient().getId());
            preparedStatement.setInt(2, object.getMachine().getId());
            preparedStatement.setLong(3, object.getFrom().getTime());
            preparedStatement.setLong(4, object.getTo().getTime());
            preparedStatement.setString(5, object.getCommandStatus().name());
            preparedStatement.execute();
        } catch (Exception e) {
            log(e.getMessage());
        }
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
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(selectUpTo);
            preparedStatement.setLong(1, new Date().getTime());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                list.add(this.generateEntity(resultSet));
            }
        } catch (Exception e) {
            log(e.getMessage());
        }
        return list;
    }

    @Override
    public int getLastIndex() {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(lastIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("lastId");
        } catch (Exception e) {
            log(e.getMessage());
            return 0;
        }
    }

    @Override
    protected Command generateEntity(ResultSet resultSet) throws SQLException {
        return new Command(
                resultSet.getInt("id"),
                new Machine(resultSet.getInt("machine_id")),
                new Client(resultSet.getInt("client_id")),
                new Date(resultSet.getLong("from")),
                new Date(resultSet.getLong("to")),
                CommandStatus.valueOf(resultSet.getString("status"))
        );
    }

    private static final String select = "SELECT * FROM command";
    private static final String selectById = "SELECT * FROM command WHERE id = ?";
    private static final String selectUpTo = "SELECT * FROM command WHERE `to` > ?";
    private static final String insert = "INSERT INTO command (client_id, machine_id, `from`, `to`, status) VALUES (?, ?, ?, ?, ?)";
    private static final String deleteById = "DELETE FROM command WHERE id = ?";
    private static final String lastIndex = "SELECT max(id) as lastId FROM command";

}

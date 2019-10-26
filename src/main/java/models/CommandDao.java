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
import java.util.Date;
import java.util.List;

@Default
@ApplicationScoped
@SuppressWarnings("FieldCanBeLocal")
public class CommandDao extends CommonDao<Command> implements CommandDaoInterface {

    @Override
    public void insert(Command object) {
        super.insert(insert, new Object[]{
                object.getClient().getId(),
                object.getMachine().getId(),
                object.getFrom().getTime(),
                object.getTo().getTime(),
                object.getCommandStatus().name()
        });
    }

    @Override
    public void update(Command object) {
        super.update(update, new Object[]{object.getCommandStatus().name(), object.getId()});
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
    public List<Command> getAll() {
        return super.getAll(select);
    }

    @Override
    public List<Command> getFuture() {
        return super.getMultiple(selectUpTo, new Object[]{new Date().getTime()});
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
    public List<Command> getByMachineId(int id) {
        return super.getMultiple(selectByMachineId, new Object[]{id});
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
    private static final String selectByMachineId = "SELECT * FROM command WHERE machine_id = ?";
    private static final String selectUpTo = "SELECT * FROM command WHERE `to` > ?";
    private static final String insert = "INSERT INTO command (client_id, machine_id, `from`, `to`, status) VALUES (?, ?, ?, ?, ?)";
    private static final String update = "UPDATE FROM command SET status = ? WHERE id = ?";
    private static final String deleteById = "DELETE FROM command WHERE id = ?";
    private static final String lastIndex = "SELECT max(id) as lastId FROM command";

}

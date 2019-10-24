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
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Command[] getAll() {
        List<Command> commandList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                commandList.add(this.generateEntity(resultSet));
            }
        } catch (Exception e) {
            log(e.getMessage());
        }
        return commandList.toArray(new Command[0]);
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

}

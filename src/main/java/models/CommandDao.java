package models;

import beans.entities.Command;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    protected Command generateEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

}

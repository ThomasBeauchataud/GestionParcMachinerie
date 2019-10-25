package models;

import beans.entities.Bill;
import beans.entities.Command;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
@ApplicationScoped
public class BillDao extends CommonDao<Bill> implements BillDaoInterface {

    @Override
    public void insert(Bill object) {
        List<String> commandList = new ArrayList<>();
        for(Command command : object.getCommandList()) {
            commandList.add(String.valueOf(command.getId()));
        }
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, object.getClient().getId());
            preparedStatement.setString(2, String.join("/", commandList));
            preparedStatement.setBoolean(3, object.isPaid());
            preparedStatement.setInt(4, object.getValue());
            preparedStatement.execute();
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    @Override
    public void update(Bill object) {

    }

    @Override
    public Bill getById(int id) {
        return null;
    }

    @Override
    public Bill[] getAll() {
        return new Bill[0];
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    protected Bill generateEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    private static final String insert = "INSERT INTO bill (user_id, commands, paid, value) VALUES (?, ?, ?, ?)";

}

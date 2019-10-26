package models;

import beans.entities.Bill;
import beans.entities.Client;
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
        super.deleteById(delete, id);
    }

    @Override
    public List<Bill> getByClientId(int id) {
        List<Bill> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(selectByClientId);
            preparedStatement.setInt(1, id);
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
    protected Bill generateEntity(ResultSet resultSet) throws SQLException {
        Bill bill = new Bill(
                resultSet.getInt("id"),
                new Client(resultSet.getInt("client_id")),
                new ArrayList<>(),
                resultSet.getInt("value"),
                resultSet.getBoolean("paid")
        );
        for(String commandId : resultSet.getString("commands").split("/")){
            bill.addCommand(new Command(Integer.parseInt(commandId)));
        }
        return bill;
    }

    private static final String insert = "INSERT INTO bill (client_id, commands, paid, value) VALUES (?, ?, ?, ?)";
    private static final String selectByClientId = "SELECT * FROM bill WHERE client_id = ?";
    private static final String delete = "DELETE FROM bill WHERE id = ?";

}

package models;

import beans.entities.Bill;
import beans.entities.Client;
import beans.entities.Command;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
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
        super.insert(insert, new Object[]{
                object.getClient().getId(),
                String.join("/", commandList),
                object.isPaid(),
                object.getValue()
        });
    }

    @Override
    public void update(Bill object) {
        super.update(update, new Object[]{object.isPaid(), object.getId()});
    }

    @Override
    public Bill getById(int id) {
        return super.getById(selectById, id);
    }

    @Override
    public List<Bill> getAll() {
        return super.getAll(select);
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(delete, id);
    }

    @Override
    public List<Bill> getByClientId(int id) {
        return this.getMultiple(selectByClientId, new Object[]{id});
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
    private static final String selectById = "SELECT * FROM bill WHERE id = ?";
    private static final String select = "SELECT * FROM bill";
    private static final String update = "UPDATE FROM bill SET paid = ? WHERE id = ?";
    private static final String delete = "DELETE FROM bill WHERE id = ?";

}

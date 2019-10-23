package models;

import beans.entities.Machine;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@ApplicationScoped
@Default
public class MachineDao extends CommonDao implements MachineDaoInterface {

    @Override
    public void insert(Machine machine) {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(insertMachine);
            preparedStatement.setString(1, machine.getFamily().name());
            preparedStatement.setString(2, machine.getModel().name());
            preparedStatement.setInt(3, machine.getRentPrice());
            preparedStatement.setInt(4, machine.getSalePrice());
            preparedStatement.setString(5, machine.getStatus().name());
            preparedStatement.execute();
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    @Override
    public void update(Machine machine) {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(updateMachine);
            preparedStatement.setString(1, machine.getFamily().name());
            preparedStatement.setString(2, machine.getModel().name());
            preparedStatement.setInt(3, machine.getRentPrice());
            preparedStatement.setInt(4, machine.getSalePrice());
            preparedStatement.setString(5, machine.getStatus().name());
            preparedStatement.setInt(6, machine.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    @Override
    public Machine getById(int id) {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(selectByMachineId);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Machine(
                    resultSet.getInt("id"),
                    resultSet.getString("family"),
                    resultSet.getString("model"),
                    resultSet.getInt("rentPrice"),
                    resultSet.getInt("salePrice"),
                    resultSet.getInt("businessDiscount"),
                    resultSet.getString("status")
            );
        } catch (Exception e) {
            log(e.getMessage());
            return null;
        }
    }

    @Override
    public Machine getByName(String name) {
        return null; // Multiple machines have the same model
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(deleteByMachineId);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    @Override
    public void deleteByName(String name) {
        // Multiple machines have the same model
    }

    private static String insertMachine = "INSERT INTO machine (family, model, rentPrice, salePrice, businessDiscount, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static String updateMachine = "UPDATE machine SET family = ?, model = ?, rentPrice = ?, salePrice = ?, businessDiscount = ?, status = ? WHERE id = ?";
    private static String selectByMachineId = "SELECT * FROM machine WHERE id = ?";
    private static String deleteByMachineId = "DELETE FROM machine WHERE id = ?";
}

package models.common;

import common.Logger;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonDao<T> {

    @Inject
    private DatabaseManagerInterface databaseManager;

    protected Connection getConnection() {
       return this.databaseManager.getConnection();
    }

    protected void log(String message) {
        Logger.log("dao", message);
    }

    protected abstract T generateEntity(ResultSet resultSet) throws SQLException;

    protected T getById(String query, int id) {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return this.generateEntity(resultSet);
        } catch (Exception e) {
            log(e.getMessage());
            return null;
        }
    }


    protected List<T> getAll(String query) {
        List<T> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                list.add(this.generateEntity(resultSet));
            }
        } catch (Exception e) {
            log(e.getMessage());
        }
        return list;
    }

    protected void deleteById(String query, int id) {
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

}

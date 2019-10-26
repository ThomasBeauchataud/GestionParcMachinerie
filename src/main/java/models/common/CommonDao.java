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

    protected void insert(String query, Object[] parameters) {
        try {
            PreparedStatement preparedStatement = this.generateStatement(query, parameters);
            preparedStatement.execute();
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    protected void update(String query, Object[] parameters) {
        try {
            PreparedStatement preparedStatement = this.generateStatement(query, parameters);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

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

    protected T getOne(String query, Object[] parameters) {
        try {
            PreparedStatement preparedStatement = this.generateStatement(query, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return this.generateEntity(resultSet);
        } catch (Exception e) {
            log(e.getMessage());
            return null;
        }
    }

    protected List<T> getMultiple(String query, Object[] parameters) {
        List<T> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.generateStatement(query, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                list.add(this.generateEntity(resultSet));
            }
        } catch (Exception e) {
            log(e.getMessage());
        }
        return list;
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

    private PreparedStatement generateStatement(String query, Object[] parameters) throws SQLException {
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
        for(int i = 0 ; i < parameters.length ; i++) {
            Object parameter = parameters[i];
            if(parameter.getClass() == Integer.class) {
                preparedStatement.setInt(i+1, (int)parameter);
            }
            if(parameter.getClass() == String.class) {
                preparedStatement.setString(i+1, (String) parameter);
            }
            if(parameter.getClass() == Boolean.class) {
                preparedStatement.setBoolean(i+1, (boolean) parameter);
            }
            if(parameter.getClass() == Long.class) {
                preparedStatement.setLong(i+1, (long) parameter);
            }
        }
        return preparedStatement;
    }

}

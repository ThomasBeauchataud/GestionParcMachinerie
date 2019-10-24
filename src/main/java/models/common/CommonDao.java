package models.common;

import common.Logger;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}

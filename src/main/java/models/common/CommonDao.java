package models.common;

import common.Logger;

import javax.inject.Inject;
import java.sql.Connection;

public abstract class CommonDao {

    @Inject
    private DatabaseManagerInterface databaseManager;

    protected Connection getConnection() {
       return this.databaseManager.getConnection();
    }

    protected void log(String message) {
        Logger.log("dao", message);
    }

}

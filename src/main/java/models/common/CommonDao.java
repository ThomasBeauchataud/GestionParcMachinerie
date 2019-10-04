package models.common;

import common.Logger;

import javax.inject.Inject;

public abstract class CommonDao {

    @Inject
    protected DatabaseManager databaseManager;

    protected void log(String message) {
        Logger.log("dao", message);
    }

}

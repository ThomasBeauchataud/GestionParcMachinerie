package common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public abstract class GenericDao {

    protected final String db_url = "jdbc:mysql://localhost:3306/gestionparcmachinerie";
    protected final String db_login = "root";
    protected final String db_password = "0000";
    private Logger logger;

    public GenericDao() {
        this.logger = Logger.getLogger("DaoLogger");
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void executeInsertQuery(String query) {
        this.logger.info("Executing : " + query);
        try {
            Connection connection = DriverManager.getConnection(this.db_url, this.db_login, this.db_password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected ResultSet executeSelectQuery(String query) {
        this.logger.info("Executing : " + query);
        ResultSet resultSet = null;
        try {
            Connection connection = DriverManager.getConnection(this.db_url, this.db_login, this.db_password);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultSet;
    }
}

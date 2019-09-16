package service.command;

import common.dao.GenericDao;
import inventory.Status;
import service.command.rent.Rent;
import service.command.rent.RentDaoInterface;
import service.command.rent.RentDao;
import service.command.sale.Sale;
import service.command.sale.SaleDaoInterface;
import service.command.sale.SaleDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CommandDao extends GenericDao implements CommandDaoInterface {

    private RentDaoInterface rendDao;
    private SaleDaoInterface saleDao;

    public CommandDao() {
        this.rendDao = new RentDao();
        this.saleDao = new SaleDao();
    }


    @Override
    public void saveCommand(Command command) {
        if(command.getClass().getName().equals(Rent.class.getName())) {
            this.rendDao.saveRent((Rent)command);
        }
        if(command.getClass().getName().equals(Sale.class.getName())) {
            this.saleDao.saveSale((Sale)command);
        }
        try {
            Connection connection = DriverManager.getConnection(db_url, db_login, db_password);
            Statement statement = connection.createStatement();
            //String query = "INSERT INTO command ";
            //ResultSet result = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package authentication.access;

import authentication.access.client.ClientAccess;
import authentication.access.client.ClientAccessDao;
import authentication.access.client.ClientAccessDaoInterface;
import authentication.access.employee.EmployeeAccess;
import authentication.access.employee.EmployeeAccessDao;
import authentication.access.employee.EmployeeAccessDaoInterface;
import common.dao.GenericDao;

import java.sql.ResultSet;

import static authentication.access.AccessTools.isClientAccess;
import static authentication.access.AccessTools.isEmployeeAccess;

public class AccessDao extends GenericDao implements AccessDaoInterface {

    private ClientAccessDaoInterface clientAccessDao;
    private EmployeeAccessDaoInterface employeeAccessDao;

    public AccessDao() {
        this.employeeAccessDao = new EmployeeAccessDao();
        this.clientAccessDao = new ClientAccessDao();
    }

    @Override
    public void saveAccess(Access access) {
        try {
            String query = "INSERT INTO access " +
                    "(username, password) VALUES (" +
                    "'" + access.getUsername() + "'," +
                    "'" + access.getPassword() + "')";
            this.executeInsertQuery(query);

            ResultSet result = this.executeSelectQuery("SELECT MAX(id) as id FROM access");
            result.next();
            access.setId(result.getInt("id"));

            if (isClientAccess(access)) {
                this.clientAccessDao.saveClientAccess((ClientAccess) access);
            }
            if (isEmployeeAccess(access)) {
                this.employeeAccessDao.saveEmployeeAccess((EmployeeAccess) access);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Access findByUsername(String username) {
        Access access = null;
        try {
            String query = "SELECT * FROM access WHERE username = '" + username + "'";
            ResultSet result = this.executeSelectQuery(query);
            result.next();
            access = clientAccessDao.findById(result.getInt("id"));
            if (access == null) {
                access = employeeAccessDao.findById(result.getInt("id"));
            }
            if (access != null) {
                access.setPassword(result.getString("password"));
                access.setUsername(username);
                access.setId(result.getInt("id"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return access;
    }
}

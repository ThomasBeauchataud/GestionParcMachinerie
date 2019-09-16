package authentication.access.employee;

import common.dao.GenericDao;

import java.sql.ResultSet;

public class EmployeeAccessDao extends GenericDao implements EmployeeAccessDaoInterface {

    @Override
    public void saveEmployeeAccess(EmployeeAccess employeeAccess) {
        String query = "INSERT INTO employee_access " +
                "(id, code) VALUES (" +
                "'" + employeeAccess.getId() + "'," +
                "'" + employeeAccess.getCode() + "')";
        this.executeInsertQuery(query);
    }

    @Override
    public EmployeeAccess findById(int id) {
        EmployeeAccess EmployeeAccess = null;
        try {
            String query = "SELECT * FROM employee_access WHERE id = " + id;
            ResultSet result = this.executeSelectQuery(query);
            result.next();
            EmployeeAccess = new EmployeeAccess();
            EmployeeAccess.setId(result.getInt("id"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return EmployeeAccess;
    }
}

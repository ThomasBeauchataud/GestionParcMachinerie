package authentication.access.employee;

import authentication.access.client.ClientAccess;

public interface EmployeeAccessDaoInterface {

    public void saveEmployeeAccess (EmployeeAccess employeeAccess);

    public EmployeeAccess findById(int id);

}

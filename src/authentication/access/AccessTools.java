package authentication.access;

import authentication.access.client.ClientAccess;
import authentication.access.employee.EmployeeAccess;

public class AccessTools {

    public static boolean isClientAccess(Access access) {
        return access.getClass() == ClientAccess.class;
    }

    public static boolean isEmployeeAccess(Access access) {
        return access.getClass() == EmployeeAccess.class;
    }
}

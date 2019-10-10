package beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionManagementBean implements Serializable {

    private String name;
    private boolean admin;
    private String adminHeaderCode;

    public SessionManagementBean() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
        if(admin) {
            this.adminHeaderCode = "admin-";
        } else {
            this.adminHeaderCode = "";
        }
    }

    public String getAdminHeaderCode() {
        return adminHeaderCode;
    }
}

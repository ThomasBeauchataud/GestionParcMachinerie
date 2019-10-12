package beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionManagement implements Serializable {

    @Inject
    private NavigationController navigationController;

    private String name;
    private boolean admin;

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
    }

    public void logout() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            name = null;
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.invalidate();
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            String casUrl = (String)env.lookup("cas-url");
            facesContext.getExternalContext().redirect( casUrl +
                    "logout?logoutFinalPage=" + navigationController.getApplicationUrl()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void isAuthenticated () {
        if(name == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(navigationController.getApplicationUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

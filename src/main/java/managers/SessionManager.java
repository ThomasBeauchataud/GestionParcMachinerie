package managers;

import beans.SessionManagementBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@ApplicationScoped
@Default
public class SessionManager implements SessionManagerInterface {

    @Inject
    private SessionManagementBean sessionManagementBean;

    @Override
    public void setSession(String name, boolean isAdmin) {
        sessionManagementBean.setName(name);
        sessionManagementBean.setAdmin(isAdmin);
    }

}

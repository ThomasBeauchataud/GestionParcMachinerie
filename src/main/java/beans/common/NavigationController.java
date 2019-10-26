package beans.common;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.Serializable;

@Named
@ApplicationScoped
public class NavigationController implements Serializable {

    private String applicationUrl;

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public String getGoToCas() {
        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            return env.lookup("cas-url") + "login?service=CAS&redirect=" + applicationUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String goToClients() {
        return "/clients/index.xhtml?faces-redirect=true";
    }

    public String goToClientCreation() {
        return "/clients/creation.xhtml?faces-redirect=true";
    }

    public String goToClientDetail(int id) {
        return "/clients/consultation.xhtml?faces-redirect=true&id="+id;
    }

    public String goToCommandCreation() {
        return "/commands/creation.xhtml?faces-redirect=true";
    }

    public String goToCommands() {
        return "/commands/index.xhtml?faces-redirect=true";
    }

    public String goToMachineCreation() { return "/inventory/machines/creation.xhtml?faces-redirect=true"; }

    public String goToMachineModification() { return "/inventory/machines/edit.xhtml?faces-redirect=true"; }

    public String goToMachines() {
        return "/inventory/machines/index.xhtml?faces-redirect=true";
    }

}

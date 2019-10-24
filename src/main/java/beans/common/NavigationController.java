package beans.common;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;

@Named
@ApplicationScoped
public class NavigationController implements Serializable {

    private String applicationUrl;
    private String goToCas;

    @PostConstruct
    public void init() {
        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            goToCas = env.lookup("cas-url") + "login?service=CAS&redirect=" + applicationUrl;
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public String getGoToCas() {
        return goToCas;
    }

    public void setGoToCas(String goToCas) {
        this.goToCas = goToCas;
    }

    public String goToClients() {
        return "/clients/index.xhtml?faces-redirect=true";
    }

    public String goToCommandCreation() {
        return "/commands/creation.xhtml?faces-redirect=true";
    }

    public String goToCommands() {
        return "/commands/index.xhtml?faces-redirect=true";
    }

    public String goToMachineCreation() {
        return "/machines/creation.xhtml?faces-redirect=true";
    }

    public String goToMachines() {
        return "/machines/index.xhtml?faces-redirect=true";
    }

}

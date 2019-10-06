package authentication;

import common.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ApplicationScoped
@Default
public class CasClient implements CasClientInterface {

    public String getNameByTicket(String ticket) {
        return "adminSR-TP2";
    }

    public boolean isValidTicket(String ticket) {
        return true;
    }

    public void redirectCas(HttpServletResponse response) {
        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            response.sendRedirect((String) env.lookup("cas-url"));
        } catch (Exception e) {
            Logger.log("cas-client", e.getMessage());
        }
    }
}

package authentication;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ApplicationScoped
@Default
public class CasClient implements CasClientInterface {

    static String urlConnection = "";

    public String getNameByTicket(String ticket) {
        return "adminSR-TP2";
    }

    public boolean isValidTicket(String ticket) {
        return true;
    }

    public void redirectCas(HttpServletResponse response) throws IOException {
        response.sendRedirect(urlConnection);
    }

}

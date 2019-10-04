package authentication;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CasClientInterface {

    String getNameByTicket(String ticket);

    boolean isValidTicket(String ticket);

    void redirectCas(HttpServletResponse request) throws IOException;

}

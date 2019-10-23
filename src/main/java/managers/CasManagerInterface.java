package managers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CasManagerInterface {

    boolean isValidTicket(String ticket);

    boolean isAuthenticated(HttpServletRequest request);

    String generateCasLoginUrl();
}

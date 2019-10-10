package servlets.authentication;

import common.ConfigManager;
import managers.CasManager;
import managers.CasManagerInterface;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private CasManagerInterface casManager;

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(casManager.isAuthenticated(request)) {
                response.sendRedirect("home");
            } else {
                String url = ConfigManager.casUrl +
                        "login?service=" + ConfigManager.applicationName + "&loginFinalPage=" +
                        request.getRequestURL().toString();
                response.sendRedirect(url);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

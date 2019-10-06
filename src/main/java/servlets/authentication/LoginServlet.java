package servlets.authentication;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            final String url = getServletContext().getInitParameter("cas-url") +
                    "login?service=GestionParcMachinerie&authPage=" +
                    request.getRequestURL().toString().replace("login","authentication");
            response.sendRedirect(url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

package servlets.authentication;

import authentication.CasClientInterface;
import managers.UserManagerInterface;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "authentication", urlPatterns = "/authentication")
public class AuthenticationServlet extends HttpServlet {

    @Inject
    private CasClientInterface casClient;
    @Inject
    private UserManagerInterface userManager;

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getSession().getAttribute("userName") == null) {
                if (request.getParameter("ticket") != null && casClient.isValidTicket(request.getParameter("ticket"))) {
                    String userName = casClient.getNameByTicket(request.getParameter("ticket"));
                    userManager.createIfNotExists(userName);
                    request.getSession().setAttribute("username", userName);
                    Context env = (Context)new InitialContext().lookup("java:comp/env");
                    response.sendRedirect(env.lookup("app-domain-url") + "home");
                }
            }
            request.getRequestDispatcher("").forward(request, response);
        }  catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

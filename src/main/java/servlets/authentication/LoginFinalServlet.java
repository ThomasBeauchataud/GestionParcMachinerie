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
                if (request.getParameter("ticket") != null
                        && request.getParameter("username") != null
                        && casClient.isValidTicket(request.getParameter("ticket"))) {
                    String userName = request.getParameter("username");
                    userManager.createIfNotExists(userName);
                    request.getSession().setAttribute("username", userName);
                    response.sendRedirect("home");
                }
            }
            request.getRequestDispatcher("").forward(request, response);
        }  catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

}

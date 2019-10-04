package servlets.authentication;

import authentication.CasClientInterface;
import managers.UserManagerInterface;

import javax.inject.Inject;
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
                    response.sendRedirect("http://localhost:8080/SR_TP2_war_exploded/passenger/travels");
                }
            }
            request.getRequestDispatcher("").forward(request, response);
        }  catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

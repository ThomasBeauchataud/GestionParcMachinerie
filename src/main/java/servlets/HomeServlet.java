package servlets;

import authentication.AuthenticationRequired;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "home", urlPatterns = "/home")
public class HomeServlet extends AbstractServlet {

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @AuthenticationRequired
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

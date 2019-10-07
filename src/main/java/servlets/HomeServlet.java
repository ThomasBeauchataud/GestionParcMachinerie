package servlets;

import servlets.authentication.AuthenticatedServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "home", urlPatterns = "/home")
public class HomeServlet extends AuthenticatedServlet {

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void handleGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void handlePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        handleGet(request, response);
    }

}

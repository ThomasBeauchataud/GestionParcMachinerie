package servlets;

import servlets.common.AuthenticatedServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "home", urlPatterns = "/home")
public class HomeServlet extends AuthenticatedServlet {

    @Override
    protected void handleGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/PAGES/home.xhtml").forward(request, response);
    }

    @Override
    protected void handlePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        handleGet(request, response);
    }

}

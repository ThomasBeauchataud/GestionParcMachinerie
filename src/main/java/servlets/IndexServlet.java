package servlets;

import beans.common.NavigationController;
import managers.CasManagerInterface;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "index", urlPatterns = "")
public class IndexServlet extends HttpServlet {

    @Inject
    private NavigationController navigationController;
    @Inject
    private CasManagerInterface casManager;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        navigationController.setApplicationUrl(request.getRequestURL().toString());
        if(casManager.isAuthenticated(request)) {
            response.sendRedirect("home.xhtml");
        } else {
            response.sendRedirect(casManager.generateCasLoginUrl());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}

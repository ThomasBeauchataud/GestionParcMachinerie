package servlets;

import managers.CasManagerInterface;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private CasManagerInterface casManager;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

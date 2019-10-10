package servlets.common;

import managers.CasManagerInterface;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AuthenticatedServlet extends AbstractServlet {

    @Inject
    private CasManagerInterface casManager;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        if(casManager.isAuthenticated(request)) {
            super.doGet(request, response);
        } else {
            try {
                response.sendRedirect("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        if(casManager.isAuthenticated(request)) {
            super.doPost(request, response);
        } else {
            try {
                response.sendRedirect("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

package servlets.authentication;

import servlets.AbstractServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AuthenticatedServlet extends AbstractServlet {

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //todo treat the authentication
        super.doGet(request, response);
    }

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        //todo treat the authentication
        super.doGet(request, response);
    }

}

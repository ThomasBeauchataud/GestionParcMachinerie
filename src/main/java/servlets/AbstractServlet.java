package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractServlet extends HttpServlet {

    protected void authenticationRequired(HttpServletRequest request, HttpServletResponse response) {
        //todo implements
    }

}

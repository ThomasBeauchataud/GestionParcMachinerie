package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet permit to a client to manage his commands
 */
@WebServlet(name = "client_command", urlPatterns = "/command")
public class ClientCommandServlet extends AbstractServlet {

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void handleGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void handlePost(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

}

package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet permit to a client to manage his commands
 */
@WebServlet(name = "client_command", urlPatterns = "/command")
public class ClientCommandServlet extends AbstractServlet {

    /**
     * Todo secure the access to this form with annotation
     * This method display the view for a client of all his commands
     * This view offer some action to manage commands
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

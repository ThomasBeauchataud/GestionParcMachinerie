package servlets;

import servlets.common.AuthenticatedServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "client_command", urlPatterns = "/command")
public class ClientCommandServlet extends AuthenticatedServlet {

    @Override
    protected void handleGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    protected void handlePost(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

}

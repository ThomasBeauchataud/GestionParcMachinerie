package servlets.common;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "error", urlPatterns = "/error")
public class ErrorServlet extends AbstractServlet {

    @Override
    protected void handleGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/PAGES/jsp/error.jsp").forward(request, response);
    }

    @Override
    protected void handlePost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    static String createErrorUrl(String message, String cause) {
        return "error?message=" + message + "&cause=" + cause;
    }

}

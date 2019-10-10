package servlets.authentication;

import common.ConfigManager;
import servlets.common.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "logout", urlPatterns = "/logout")
public class LogoutServlet extends AbstractServlet {

    @Override
    protected void handleGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute("active");
        request.getSession().invalidate();
        String url = ConfigManager.casUrl +
                "logout?logoutFinalPage=" +
                request.getRequestURL().toString().replace("logout","login");
        response.sendRedirect(url);
    }

    @Override
    protected void handlePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        handleGet(request, response);
    }
}

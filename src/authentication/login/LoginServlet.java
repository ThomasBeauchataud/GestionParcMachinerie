package authentication.login;

import authentication.access.Access;
import authentication.access.AccessDao;
import authentication.access.AccessDaoInterface;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private AccessDaoInterface accessDao;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.accessDao = new AccessDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Access access = null;

        try {
            access = this.accessDao.findByUsername(request.getParameter("username"));
        } catch (Exception e) {
            //todo redirect with error message unexisting access
        }

        HttpSession session = request.getSession();

        assert access != null;
        if(access.getPassword().equals(request.getParameter("password"))) {
            session.setAttribute("access", access);
        } else {

        }

        response.sendRedirect("index");
    }
}

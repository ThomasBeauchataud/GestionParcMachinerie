import authentication.access.Access;
import authentication.access.client.ClientAccess;
import authentication.access.employee.EmployeeAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static authentication.access.AccessTools.isClientAccess;
import static authentication.access.AccessTools.isEmployeeAccess;

@WebServlet(name = "index", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("access") == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            Access access = (Access)session.getAttribute("access");

            if(isClientAccess(access)) {
                request.getRequestDispatcher("index_client.jsp").forward(request, response);
            }
            if(isEmployeeAccess(access)) {
                request.getRequestDispatcher("index_commercial.jsp").forward(request, response);
            }
        }
    }
}

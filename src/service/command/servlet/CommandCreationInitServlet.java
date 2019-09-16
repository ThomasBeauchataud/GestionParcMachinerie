package service.command.servlet;

import authentication.access.client.ClientAccess;
import authentication.access.Access;
import inventory.machine.MachineDao;
import inventory.machine.MachineDaoInterface;
import person.client.Client;
import person.client.ClientDaoInterface;
import person.client.ClientDao;
import service.command.Command;
import service.command.CommandDao;
import service.command.CommandDaoInterface;
import service.command.rent.Rent;
import service.command.sale.Sale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static authentication.access.AccessTools.isClientAccess;

/**
 * This servlet manage this first part of the creation of commands for commercials and clients
 */
@WebServlet(name = "command_creation_init", urlPatterns = "/command/creation/1")
public class CommandCreationInitServlet extends HttpServlet {

    private ClientDaoInterface clientDao;
    private CommandDaoInterface commandDao;
    private MachineDaoInterface machineDao;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.clientDao = new ClientDao();
        this.commandDao = new CommandDao();
        this.machineDao = new MachineDao();
    }

    /**
     * Todo secure the access to this form with annotation
     * This method display the first view for the creation of a command, depending on the logged user
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Access access = (Access)session.getAttribute("access");

        if(isClientAccess(access)) {
            request.getRequestDispatcher("/command_creation_init_client.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/command_creation_init_employee.jsp").forward(request, response);
        }
    }

    /**
     * Todo secure the access to this form with annotation
     * This method handle the first form for the creation of a new command
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            Access access = (Access)session.getAttribute("access");
            Client client;

            if(isClientAccess(access)) {
                client = clientDao.find((ClientAccess)access);
            } else {
                client = clientDao.find(request.getParameter("name"));
            }

            Command command;
            Date endDate = null;

            if(request.getAttribute("command_type") == "sale") {
                command = new Sale();
            } else {
                command = new Rent();
                endDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("end_date"));
            }
            command.setClient(client);
            this.commandDao.saveCommand(command);

            request.setAttribute("client", client);
            request.setAttribute("command", command);
            request.setAttribute("machines", this.machineDao.getFreeMachines(
                    new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("start_date")),
                    endDate
            ));
            request.getRequestDispatcher("/command_creation.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

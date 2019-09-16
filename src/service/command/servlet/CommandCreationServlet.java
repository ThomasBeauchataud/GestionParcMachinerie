package service.command.servlet;

import inventory.machine.Machine;
import inventory.machine.MachineModel;
import service.command.Command;
import service.command.CommandDao;
import service.command.CommandDaoInterface;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "command_creation", urlPatterns = "/command/creation/2")
public class CommandCreationServlet extends HttpServlet {

    private CommandDaoInterface commandDao;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.commandDao = new CommandDao();
    }

    /**
     * Todo secure the access to this form with annotation
     * This method handle the form of the last form to create a command
     * It redirect the user
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Machine> machines = new ArrayList<Machine>();
        Command command = (Command)request.getAttribute("command");
        ArrayList<Machine> machineList = (ArrayList<Machine>)request.getAttribute("machines");
        for(String model : Stream.of(MachineModel.values()).map(Enum::name).collect(Collectors.toList())) {
            try {
                int modelCount = Integer.parseInt(request.getParameter(model));
                for(int i = 0 ; i < machineList.size() ; i++) {
                    for(Machine machine : machineList) {
                        if(machine.getModel().toString().equals(model)) {
                            machines.add(machine);
                            modelCount--;
                        }
                        if(modelCount == 0) {
                            i = machineList.size();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        command.addMachines(machines);
        //todo add accessory
        //todo add delivery
        //todo validate the command and change to status of machines and accessories
        //todo redirect to an other page to realise the bill and the payment
    }
}

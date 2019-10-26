package managers;

import beans.entities.Bill;
import beans.entities.Client;
import beans.entities.Command;
import models.BillDaoInterface;
import models.CommandDaoInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Default
@ApplicationScoped
public class BillManager implements BillManagerInterface {

    @Inject
    private CommandManagerInterface commandManager;
    @Inject
    private CommandDaoInterface commandDao;
    @Inject
    private BillDaoInterface billDao;
    @Inject
    private ClientManagerInterface clientManager;

    @Override
    public void generateBill(List<Command> commandList, String email) {
        Bill bill = new Bill();
        int total = 0;
        int commandIndex = commandDao.getLastIndex() + 1;
        for(Command command : commandList) {
            command.setId(commandIndex);
            commandIndex++;
            total += command.getMachine().getRentPrice();
        }
        bill.setCommandList(commandList);
        bill.setClient(clientManager.findClientByEmail(email));
        total = this.generateDiscount(total, commandList);
        bill.setValue(total);
        //TODO Here we declare that bills are always paid
        bill.setPaid(true);
        billDao.insert(bill);
    }

    @Override
    public List<Bill> findBillsByClient(Client client) {
        List<Bill> billList = billDao.getByClientId(client.getId());
        for(Bill bill : billList) {
            bill.setClient(client);
            this.enrich(bill);
        }
        return billList;
    }

    /**
     * Manage discount rules
     * @param total int
     * @param commands List<Command>
     * @return int
     */
    private int generateDiscount(int total, List<Command> commands) {
        if(commands.size() > 10) {
            if(commands.size() > 20) {
                if(commands.size() > 30) {
                    return (int) (total * 0.7);
                }
                return (int) (total * 0.8);
            }
            return (int) (total * 0.9);
        }
        return total;
    }

    private void enrich(Bill bill) {
        List<Command> finalCommandList = new ArrayList<>();
        bill.setClient(clientManager.findClientById(bill.getClient().getId()));
        for(Command command : bill.getCommandList()) {
            finalCommandList.add(commandManager.findCommandById(command.getId()));
        }
        bill.setCommandList(finalCommandList);
    }

}

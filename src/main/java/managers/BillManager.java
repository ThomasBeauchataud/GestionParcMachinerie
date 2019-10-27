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
    @Inject
    private DiscountManagerInterface discountManager;

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
        Client client = clientManager.findClientByEmail(email);
        bill.setClient(client);
        total = discountManager.generateDiscount(total, commandList, client);
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

    private void enrich(Bill bill) {
        List<Command> finalCommandList = new ArrayList<>();
        bill.setClient(clientManager.findClientById(bill.getClient().getId()));
        for(Command command : bill.getCommandList()) {
            finalCommandList.add(commandManager.findCommandById(command.getId()));
        }
        bill.setCommandList(finalCommandList);
    }

}

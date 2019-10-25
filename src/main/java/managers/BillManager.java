package managers;

import beans.entities.Bill;
import beans.entities.Command;
import models.BillDaoInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
@ApplicationScoped
public class BillManager implements BillManagerInterface {

    @Inject
    private BillDaoInterface billDao;
    @Inject
    private ClientManagerInterface clientManager;

    @Override
    public void generateBill(List<Command> commandList, String email) {
        Bill bill = new Bill();
        bill.setCommandList(commandList);
        int total = 0;
        for(Command command : commandList) {
            total += command.getMachine().getRentPrice();
        }
        bill.setClient(clientManager.findClientByEmail(email));
        total = this.generateDiscount(total, commandList);
        bill.setValue(total);
        billDao.insert(bill);
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

}

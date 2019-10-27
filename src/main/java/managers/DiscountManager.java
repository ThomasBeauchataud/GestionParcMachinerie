package managers;

import beans.entities.Client;
import beans.entities.Command;

import java.util.List;

public class DiscountManager implements DiscountManagerInterface {

    @Override
    public int generateDiscount(int total, List<Command> commands, Client client) {
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

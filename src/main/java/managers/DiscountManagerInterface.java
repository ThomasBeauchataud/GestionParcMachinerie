package managers;

import beans.entities.Client;
import beans.entities.Command;

import java.util.List;

public interface DiscountManagerInterface {

    /**
     * Return the Real price of the Bill calculated after discount
     * @param total int the initial price
     * @param commands List<Command>
     * @param client Client
     * @return int
     */
    int generateDiscount(int total, List<Command> commands, Client client);

}

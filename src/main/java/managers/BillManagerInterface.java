package managers;

import beans.entities.Bill;
import beans.entities.Client;
import beans.entities.Command;

import java.util.List;

public interface BillManagerInterface {

    /**
     * Generate a save a new Bill with Commands and Client identified by his email
     * @param commandList List<Command>
     * @param email String the email of the client
     */
    void generateBill(List<Command> commandList, String email);

    /**
     * Return the list of Bills associated to a Client
     * @param client Client
     * @return List<Bill>
     */
    List<Bill> findBillsByClient(Client client);

}

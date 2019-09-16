package person.client;

import authentication.access.client.ClientAccess;
import person.Person;

public class Client extends Person {

    private ClientAccess clientAccess;
    private String companyName;

    public ClientAccess getUserAccess() {
        return clientAccess;
    }

    public void setUserAccess(ClientAccess clientAccess) {
        this.clientAccess = clientAccess;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

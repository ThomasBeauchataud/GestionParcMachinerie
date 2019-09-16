package person.client;

import authentication.access.client.ClientAccess;
import common.dao.GenericDao;

import java.sql.ResultSet;

public class ClientDao extends GenericDao implements ClientDaoInterface {

    @Override
    public void save(Client client)  {

    }

    @Override
    public Client find(String name) {
        Client client = null;
        try {
            String query = "SELECT * FROM person p " +
                    "LEFT JOIN client c ON c.id = p.id " +
                    "WHERE name = '" + name + "' " +
                    "AND company_name IS NOT NULL";
            ResultSet result = this.executeSelectQuery(query);
            result.next();
            if(result.getString("name").equals(name)) {
                client = new Client();
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setSurname(result.getString("surname"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return client;
    }

    @Override
    public Client find(ClientAccess clientAccess) {
        Client client = null;
        try {
            String query = "SELECT * FROM person p " +
                    "LEFT JOIN client c ON c.id = p.id " +
                    "WHERE id = " + clientAccess.getId() + " " +
                    "AND company_name IS NOT NULL";
            ResultSet result = this.executeSelectQuery(query);
            result.next();
            if(result.getInt("id") == clientAccess.getId()) {
                client = new Client();
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setSurname(result.getString("surname"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return client;
    }

}

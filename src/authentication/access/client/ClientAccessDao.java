package authentication.access.client;

import common.dao.GenericDao;

import java.sql.ResultSet;

public class ClientAccessDao extends GenericDao implements ClientAccessDaoInterface {

    @Override
    public void saveClientAccess(ClientAccess clientAccess) {
        String query = "INSERT INTO client_access " +
                "(id) VALUES (" +
                "'" + clientAccess.getId() + "')";
        this.executeInsertQuery(query);
    }

    @Override
    public ClientAccess findById(int id) {
        ClientAccess clientAccess = null;
        try {
            String query = "SELECT * FROM client_access WHERE id = " + id;
            ResultSet result = this.executeSelectQuery(query);
            result.next();
            if(result.getInt("id") == id) {
                clientAccess = new ClientAccess();
                clientAccess.setId(id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return clientAccess;
    }
}

package authentication.access.client;

public interface ClientAccessDaoInterface {

    public void saveClientAccess (ClientAccess clientAccess);

    public ClientAccess findById(int id);

}

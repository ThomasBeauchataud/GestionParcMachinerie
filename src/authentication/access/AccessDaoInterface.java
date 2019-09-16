package authentication.access;

public interface AccessDaoInterface {

    public void saveAccess(Access access);

    public Access findByUsername(String username);

}

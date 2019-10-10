package managers;

public interface SessionManagerInterface {

    /**
     * Set the SessionManagementBean
     * @param name Sting the name of the session
     * @param isAdmin boolean
     */
    void setSession(String name, boolean isAdmin);

}

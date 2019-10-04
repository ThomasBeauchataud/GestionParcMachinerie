package managers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@ApplicationScoped
@Default
public class UserManager implements UserManagerInterface {

    @Override
    public void createIfNotExists(String userName) {

    }
}

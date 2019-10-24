package managers;

import beans.entities.internal.MachineCatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Default
public class CatalogManager implements CatalogManagerInterface {

    @Inject
    private MachineManagerInterface machineManager;
    @Inject
    private CommandManagerInterface commandManager;

    @Override
    public List<MachineCatalog> findMachineCatalog() {
        return null;
    }
}

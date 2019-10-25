package managers;

import beans.entities.Machine;
import beans.entities.internal.MachineCatalog;

import java.util.List;

public interface CatalogManagerInterface {

    /**
     * Return the MachineCatalog List
     * @return List<MachineCatalog>
     */
    List<MachineCatalog> findMachineCatalog();

    /**
     * Reload the Catalog
     */
    void loadMachineCatalog();

}

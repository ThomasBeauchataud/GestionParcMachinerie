package managers;

import beans.entities.Command;
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

    /**
     * Generate a list of Commands with a list of MachineCatalog
     * @param machineCatalogList List<MachineCatalog>
*      @param clientEmail String
     * @return List<Command>
     */
    List<Command> generateCommandsWithCatalogs(List<MachineCatalog> machineCatalogList, String clientEmail);

}

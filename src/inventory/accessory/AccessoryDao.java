package inventory.accessory;

import common.dao.GenericDao;

public class AccessoryDao extends GenericDao implements AccessoryDaoInterface {
    
    @Override
    public void saveAccessory(Accessory accessory) {
        String query = "INSERT INTO accessory " +
                "(family, model, rentPrice, salePrice, status) VALUES (" +
                "'" + accessory.getFamily().toString() + "'," +
                "'" + accessory.getModel().toString() + "'," +
                "'" + accessory.getRentPrice() + "'," +
                "'" + accessory.getSalePrice() + "'," +
                "'" + accessory.getStatus().toString() + "')";
        this.executeInsertQuery(query);
    }
}

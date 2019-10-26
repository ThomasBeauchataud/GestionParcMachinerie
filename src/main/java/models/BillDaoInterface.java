package models;

import beans.entities.Bill;
import models.common.CommonDaoInterface;

import java.util.List;

public interface BillDaoInterface extends CommonDaoInterface<Bill> {

    /**
     * Return a list of Bills from the database with a specific clientId
     * @param id int
     * @return List<Bill>
     */
    List<Bill> getByClientId(int id);

}

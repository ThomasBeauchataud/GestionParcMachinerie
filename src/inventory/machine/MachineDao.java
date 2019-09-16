package inventory.machine;

import common.dao.GenericDao;
import inventory.Status;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class MachineDao extends GenericDao implements MachineDaoInterface {

    @Override
    public ArrayList<Machine> getFreeMachines(String model, int count, Date startDate, Date endDate) {
        ArrayList<Machine> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM machine " +
                    "INNER JOIN machine_command mc on machine.id = mc.machine" +
                    "INNER JOIN command c on machine.id = c.machine" +
                    "INNER JOIN rent r on c.id = r.id" +
                    "WHERE model = '" + model + "'" +
                    "AND status = '" + Status.available.toString() + "'" +
                    "AND (r.endRent = NULL OR (";
            if(endDate == null) {
                query += startDate + " > r.endRent ";
            } else {
                query += " r.endRent < " + startDate + " OR r.startRent > " + endDate;
            }
            query += ")) LIMIT " + count;
            ResultSet result = this.executeSelectQuery(query);

            while (result.next()) {
                Machine machine = new Machine();
                machine.setId(result.getInt("id"));
                machine.setFamily(MachineFamily.valueOf(result.getString("family")));
                machine.setModel(MachineModel.valueOf(result.getString("model")));
                machine.setStatus(Status.available);
                machine.setRentPrice(result.getInt("rentPrice"));
                machine.setSalePrice(result.getInt("salePrice"));
                list.add(machine);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public ArrayList<Machine> getFreeMachines(Date startDate, Date endDate) {
        ArrayList<Machine> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM machine " +
                    "LEFT JOIN machine_command mc ON machine.id = mc.machine " +
                    "LEFT JOIN command c ON machine.id = c.machine " +
                    "LEFT JOIN rent r ON c.id = r.id " +
                    "WHERE st = '" + Status.available.toString() + "' " +
                    "AND (r.endRent IS NULL OR ";
            if(endDate == null) {
                query += new java.sql.Date(startDate.getTime()) + " > r.endRent ";
            } else {
                query += "( r.endRent < " + new java.sql.Date(startDate.getTime()) + " OR r.startRent > " + new java.sql.Date(endDate.getTime()) + ")";
            }
            query += ")";
            ResultSet result = this.executeSelectQuery(query);

            while (result.next()) {
                Machine machine = new Machine();
                machine.setId(result.getInt("id"));
                machine.setFamily(MachineFamily.valueOf(result.getString("family")));
                machine.setModel(MachineModel.valueOf(result.getString("model")));
                machine.setStatus(Status.available);
                machine.setRentPrice(result.getInt("rentPrice"));
                machine.setSalePrice(result.getInt("salePrice"));
                list.add(machine);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void saveMachine(Machine machine) {
        String query = "INSERT INTO machine " +
                "(family, model, rentPrice, salePrice, st) VALUES (" +
                "'" + machine.getFamily().toString() + "'," +
                "'" + machine.getModel().toString() + "'," +
                "'" + machine.getRentPrice() + "'," +
                "'" + machine.getSalePrice() + "'," +
                "'" + machine.getStatus().toString() + "')";
        this.executeInsertQuery(query);
    }
}

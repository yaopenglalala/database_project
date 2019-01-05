package dao.classDao.entityDao.equipmentDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.equipment.OutdoorEquipment;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * Created by your dad on 2019/1/4.
 */
public class OutdoorEquipmentDao extends JdbcDaoImpl<OutdoorEquipment> {

    private static Connection connection = JDBCUtil.getConnection();

    public OutdoorEquipmentDao(){
        init();
    }

    public boolean addOutdoorEquipment(OutdoorEquipment outdoorEquipment) {
        String sql = "INSERT INTO outdoor_equipment (community_id, type,description,state) " +
                "values (?,?,?,?)";
        update(connection,sql,outdoorEquipment.getCommunity_id(), outdoorEquipment.getType(),outdoorEquipment.getDescription(),outdoorEquipment.getState());
        return true;
    }

    public OutdoorEquipment getOutdoorEquipmentId(Integer id){
        String sql = "SELECT * " +
                "FROM outdoor_equipment where equipment_id = ?";
        return get(connection, sql, id);
    }

    public boolean removeOutdoorEquipment(int outdoorEquipment){
        if (getOutdoorEquipmentId(outdoorEquipment) == null) return false;
        String sql = "DELETE FROM outdoor_equipment where equipment_id = ?";
        update(connection,sql,outdoorEquipment);
        return true;
    }

    public boolean updateOutdoorEquipment(OutdoorEquipment outdoorEquipment){
        if (getOutdoorEquipmentId(outdoorEquipment.getEquipment_id()) == null) return false;
        String sql = "UPDATE outdoor_equipment SET state=? where equipment_id = ?";
        update(connection,sql,outdoorEquipment.getState(),outdoorEquipment.getEquipment_id());
        return true;
    }

    public List<OutdoorEquipment> getAllIndoorEquipment(){
        String sql = "SELECT * " +
                "FROM outdoor_equipment ORDER BY equipment_id DESC";
        return getList(connection, sql);
    }

    private static void init() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF not exists outdoor_equipment\n" +
                    "(\n" +
                    "    equipment_id INT(32) PRIMARY KEY NOT NULL,\n" +
                    "    community_id INT(32) NOT NULL,\n" +
                    "    type VARCHAR(255) NOT NULL,\n" +
                    "    description VARCHAR(255),\n" +
                    "    state INT(2) DEFAULT '0' NOT NULL\n" +
                    ");");
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package dao.classDao.entityDao.equipmentDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.equipment.IndoorEquipment;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * Created by your dad on 2019/1/4.
 */
public class IndoorEquipmentDao extends JdbcDaoImpl<IndoorEquipment> {
    private static Connection connection = JDBCUtil.getConnection();

    public IndoorEquipmentDao(){
        init();
    }
    private static void init() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF not exists indoor_equipment\n" +
                            "(\n" +
                            "    equipment_id INT(32) PRIMARY KEY NOT NULL,\n" +
                            "    building_id INT(32) NOT NULL,\n" +
                            "    type VARCHAR(255) NOT NULL,\n" +
                            "    description VARCHAR(255),\n" +
                            "    state INT(2) DEFAULT '0' NOT NULL\n" +
                            ");"
            );
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addIndoorEquipment(IndoorEquipment indoorEquipment) {
        String sql = "INSERT INTO indoor_equipment (equipment_id, building_id, type,description,state) " +
                "values (?,?,?,?,?)";
        update(connection,sql,indoorEquipment.getEquipment_id(), indoorEquipment.getBuilding_id(), indoorEquipment.getType(),indoorEquipment.getDescription(),indoorEquipment.getState());
        return true;
    }

    public IndoorEquipment getIndoorEquipmentId(Integer id){
        String sql = "SELECT * " +
                "FROM indoor_equipment where equipment_id = ?";
        return get(connection, sql, id);
    }

    public boolean removeIndoorEquipment(int indoorEquipmentId){
        if (getIndoorEquipmentId(indoorEquipmentId) == null) return false;
        String sql = "DELETE FROM indoor_equipment where equipment_id = ?";
        update(connection,sql,indoorEquipmentId);
        return true;
    }

    public boolean updateIndoorEquipment(IndoorEquipment indoorEquipment){
        if (getIndoorEquipmentId(indoorEquipment.getEquipment_id()) == null) return false;
        String sql = "UPDATE indoor_equipment SET state=? where equipment_id = ?";
        update(connection,sql,indoorEquipment.getState(),indoorEquipment.getEquipment_id());
        return true;
    }

    public List<IndoorEquipment> getAllIndoorEquipment(){
        String sql = "SELECT * " +
                "FROM indoor_equipment ORDER BY equipment_id DESC";
        return getList(connection, sql);
    }




}

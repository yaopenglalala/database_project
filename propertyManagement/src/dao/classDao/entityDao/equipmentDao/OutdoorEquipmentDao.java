package dao.classDao.entityDao.equipmentDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.equipment.OutdoorEquipment;
import model.entity.house.Community;

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

    public OutdoorEquipment getOutEquipByEquipId(Integer id){
        String sql = "SELECT * " +
                "FROM outdoor_equipment where equipment_id = ?";
        return get(connection, sql, id);
    }

    public boolean removeOutdoorEquipment(int outdoorEquipment){
        if (getOutEquipByEquipId(outdoorEquipment) == null) return false;
        String sql = "DELETE FROM outdoor_equipment where equipment_id = ?";
        update(connection,sql,outdoorEquipment);
        return true;
    }

    public boolean updateOutdoorEquipment(OutdoorEquipment outdoorEquipment){
        if (getOutEquipByEquipId(outdoorEquipment.getEquipment_id()) == null) return false;
        String sql = "UPDATE outdoor_equipment SET state=? where equipment_id = ?";
        update(connection,sql,outdoorEquipment.getState(),outdoorEquipment.getEquipment_id());
        return true;
    }

    public List<OutdoorEquipment> getOutEquipByState(Community community, int state){
        String sql = "SELECT * " +
                "FROM outdoor_equipment where community_id = ? and state = ?";
        return getList(connection, sql, community.getCommunityId(), state);
    }

    public List<OutdoorEquipment> getOutEquipByCommunity(Community community){
        String sql = "SELECT * FROM outdoor_equipment where community_id = ? ";
        return getList(connection, sql, community.getCommunityId());
    }

    public List<OutdoorEquipment> getAllOutdoorEquipment(){
        String sql = "SELECT * " +
                "FROM outdoor_equipment";
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

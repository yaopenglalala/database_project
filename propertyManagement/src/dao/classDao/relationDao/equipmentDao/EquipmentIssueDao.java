package dao.classDao.relationDao.equipmentDao;
import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.equipment.EquipmentIssue;

import java.sql.Connection;
import java.util.List;

/**
 * Created by MoonBird on 2019/1/4.
 */
public class EquipmentIssueDao extends JdbcDaoImpl<EquipmentIssue> {
    private static Connection connection = JDBCUtil.getConnection();

    public EquipmentIssueDao(){
        init();
    }

    public boolean addIssue(EquipmentIssue equipmentIssue){
        String sql = "INSERT INTO equipment_issue (house_id, equipment_id, repair_id, type, description, time) " +
                "value (?,?,?,?,?,?)";
        return update(connection, sql) != 0;
    }

    public boolean modifyRepairId(int issueId, int repairId){
        String sql = "UPDATE equipment_issue SET repair_id = ? where feedback_id = ?";
        return (update(connection, sql, repairId, issueId) != 0);
    }

    public List<EquipmentIssue> getNotSolvedIssues(int buildingId){
        String sql = "SELECT * FROM equipment_issue where building_id = ? and repair_id is null";
        return getList(connection, sql, buildingId);
    }

    public List<EquipmentIssue> getSolvingIssues(int buildingId){
        String sql = "SELECT * FROM equipment_issue where building_id = ? and repair_id is not null";
        return getList(connection, sql, buildingId);
    }

    public List<EquipmentIssue> getIssuesByBuildingId(int buildingId){
        String sql = "SELECT feedback_id, house_id, equipment_id, repair_id, type, description, time " +
                "FROM HouseService natural join equipment_issue where building_id = ?";
        return getList(connection, sql, buildingId);
    }

    public List<EquipmentIssue> getIssuesByEquipId(int equipId){
        String sql = "SELECT * " +
                "FROM equipment_issue where equipment_id = ?";
        return getList(connection, sql, equipId);
    }

    public List<EquipmentIssue> getIssuesByHouseId(int houseId){
        String sql = "SELECT * FROM equipment_issue where house_id = ?";
        return getList(connection, sql, houseId);
    }

    public EquipmentIssue getIssueByFBId(int issueId){
        String sql = "SELECT * FROM equipment_issue where feedback_id = ?";
        return get(connection, sql, issueId);
    }

    private static void init(){

    }
}

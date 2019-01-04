package dao.classDao.relationDao.equipmentDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.equipment.IndoorEquipRepair;

import java.sql.Connection;
import java.util.List;

/**
 * Created by MoonBird on 2019/1/4.
 */
public class InEquipRepairDao extends JdbcDaoImpl<IndoorEquipRepair> {
    private static Connection connection = JDBCUtil.getConnection();

    public boolean addRepair(IndoorEquipRepair repair){
        String sql = "INSERT INTO in_equip_repair (equipment_id, state, cost, time) " +
                "value (?,?,?,?)";
        return update(connection, sql, repair.getEquipment_id(), repair.getState(), repair.getCost(), repair.getTime()) != 0;
    }

    public boolean modifyRepairState(int repairId, int state){
        String sql = "UPDATE in_equip_repair SET state = ? where repair_id = ?";
        return update(connection, sql, state, repairId) != 0;
    }

    public List<IndoorEquipRepair> getRepairsByEquimentId(int equipmentId){
        String sql = "SELECT * FROM in_equip_repair where equipment_id = ?";
        return getList(connection, sql, equipmentId);
    }

    private static void init(){

    }
}
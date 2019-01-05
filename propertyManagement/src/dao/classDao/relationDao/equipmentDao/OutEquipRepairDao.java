package dao.classDao.relationDao.equipmentDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.equipment.OutdoorEquipRepair;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by MoonBird on 2019/1/4.
 */
public class OutEquipRepairDao extends JdbcDaoImpl<OutdoorEquipRepair> {
    private static Connection connection = JDBCUtil.getConnection();

    public boolean addRepair(OutdoorEquipRepair repair) {
        String sql = "INSERT INTO out_equip_repair (equipment_id, state, cost, time) " +
                "value (?,?,?,?)";
        return update(connection, sql, repair.getEquipment_id(), repair.getState(), repair.getCost(), repair.getTime()) != 0;
    }

    public boolean modifyRepairState(int repairId, int state) {
        String sql = "UPDATE out_equip_repair SET state = ? where repair_id = ?";
        return update(connection, sql, state, repairId) != 0;
    }

    public List<OutdoorEquipRepair> getRepairsByEquimentId(int equipmentId) {
        String sql = "SELECT * FROM out_equip_repair where equipment_id = ?";
        return getList(connection, sql, equipmentId);
    }

    public List<OutdoorEquipRepair> getAllRepairs() {
        String sql = "SELECT * FROM out_equip_repair where cost > 0";
        return getList(connection, sql);
    }

    public List<OutdoorEquipRepair> getRepairsByTime(Date start, Date end) {
        String sql = "SELECT * FROM out_equip_repair where time > ? and time < ?";
        return getList(connection, sql);
    }

    private static void init() {

    }
}

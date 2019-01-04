package dao.classDao.relationDao.equipmentDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.equipment.OutdoorEquipCheck;

import java.sql.Connection;
import java.util.List;

/**
 * Created by MoonBird on 2019/1/4.
 */
public class OutEquipCheckDao extends JdbcDaoImpl<OutdoorEquipCheck> {
    private static Connection connection = JDBCUtil.getConnection();

    public OutEquipCheckDao(){
        init();
    }

    public boolean addCheck(OutdoorEquipCheck check){
        String sql = "INSERT INTO out_equip_check (equipment_id, sate, time)" +
                "value (?,?,?)";
        return update(connection, sql, check.getEquipment_id(), check.getState(), check.getTime()) != 0;
    }

    public List<OutdoorEquipCheck> getChecksByEquipId(int equipId){
        String sql = "SELECT * FROM out_equip_check where equipment_id = ?";
        return getList(connection, sql, equipId);
    }

    public OutdoorEquipCheck getCheckByCheckId(int checkId){
        String sql = "SELECT * FROM out_equip_check where check_id = ?";
        return get(connection, sql, checkId);
    }

    private static void init(){

    }
}

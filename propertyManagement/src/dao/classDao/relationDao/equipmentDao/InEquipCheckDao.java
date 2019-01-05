package dao.classDao.relationDao.equipmentDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.equipment.IndoorEquipCheck;

import java.sql.Connection;
import java.util.List;

/**
 * Created by MoonBird on 2019/1/4.
 */
public class InEquipCheckDao extends JdbcDaoImpl<IndoorEquipCheck> {
    private static Connection connection = JDBCUtil.getConnection();

    public InEquipCheckDao(){
        init();
    }

    public boolean addCheck(IndoorEquipCheck check){
        String sql = "INSERT INTO in_equip_check (equipment_id, state, time)" +
                "value (?,?,?)";
        return update(connection, sql, check.getEquipment_id(), check.getState(), check.getTime()) != 0;
    }

    public List<IndoorEquipCheck> getChecksByEquipId(int equipId){
        String sql = "SELECT * FROM in_equip_check where equipment_id = ?";
        return getList(connection, sql, equipId);
    }

    public IndoorEquipCheck getCheckByCheckId(int checkId){
        String sql = "SELECT * FROM in_equip_check where check_id = ?";
        return get(connection, sql, checkId);
    }

    private static void init(){

    }
}

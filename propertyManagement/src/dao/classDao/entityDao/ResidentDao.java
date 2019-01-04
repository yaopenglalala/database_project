package dao.classDao.entityDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.Resident;

import java.sql.Connection;
import java.util.List;

/**
 * Created by your dad on 2019/1/4.
 */
public class ResidentDao extends JdbcDaoImpl<Resident> {
    private static Connection connection = JDBCUtil.getConnection();

    public ResidentDao(){
        init();
    }
    private static void init(){}

    public boolean addResident(Resident resident) {
        String sql = "INSERT INTO resident (resident_id, identity_card, name,tel) " +
                "values (?,?,?,?)";
        update(connection,sql,resident.getResident_id(), resident.getIdentity_card(), resident.getName(),resident.getTel());
        return true;
    }

    public Resident getResidentByResidentId(int residentId){
        String sql = "SELECT * FROM resident where resident_id = ?";
        return get(connection, sql, residentId);
    }

    public Resident getResidentByIdentity(String identity){
        String sql = "SELECT * FROM resident where identity_card = ?";
        return get(connection, sql, identity);
    }

    public List<Resident> getAllResident(){
        String sql = "SELECT * " +
                "FROM resident ORDER BY resident_id DESC";
        return getList(connection, sql);
    }

}

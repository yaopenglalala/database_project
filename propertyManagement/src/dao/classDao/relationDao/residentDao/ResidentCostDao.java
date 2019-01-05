package dao.classDao.relationDao.residentDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.resident.ResidentCost;

import java.sql.Connection;
import java.util.List;

/**
 * Created by MoonBird on 2019/1/4.
 */
public class ResidentCostDao  extends JdbcDaoImpl<ResidentCost> {
    private static Connection connection = JDBCUtil.getConnection();

    public ResidentCostDao(){
        init();
    }

    public boolean addCost(ResidentCost cost){
        String sql = "INSERT INTO resident_cost (resident_id, description, cost, time, state)" +
                " value (?, ?, ?, ?, ?)";
        return update(connection, sql, cost.getResident_id(), cost.getDescription(),
                cost.getCost(), cost.getTime(), cost.getState()) != 0;
    }

    public boolean modifyState(int costId, int state){
        String sql = "UPDATE resident_cost SET state = ? where cost_id = ?";
        return update(connection, sql, costId, state) != 0;
    }

    public List<ResidentCost> getCostsByResidentId(int residentId){
        String sql = "SELECT * FROM resident_cost where resident_id = ?";
        return getList(connection, sql, residentId);
    }

    private static void init(){

    }
}

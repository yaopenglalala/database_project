package dao.classDao.entityDao.parkingspaceDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.parkingspace.LeasedParkingSpace;

import java.sql.Connection;
import java.util.List;

/**
 * Created by your dad on 2019/1/4.
 */
public class LeasedParkingSpaceDao extends JdbcDaoImpl<LeasedParkingSpace> {
    private static Connection connection = JDBCUtil.getConnection();
    public LeasedParkingSpaceDao(){
        init();
    }
    private static void init(){}
    public boolean addLeasedParkingSpaceDao(LeasedParkingSpace leasedParkingSpace) {
        String sql = "INSERT INTO leased_parking_space (parking_space_id, community_id, description) " +
                "values (?,?,?)";
        update(connection,sql,leasedParkingSpace.getParking_space_id(), leasedParkingSpace.getCommunity_id(), leasedParkingSpace.getDescription());
        return true;
    }
  /*  public OwnedParkingSpace getOwnedParkingSpace(int temporaryParkingSpaceId){
        String sql = "SELECT * " +
                "FROM temporary_parking_space where answer_id = ? ";
        return get(connection, sql, temporaryParkingSpaceId);
    }*/

    public List<LeasedParkingSpace> getAllLeasedParkingSpace(){
        String sql = "SELECT * " +
                "FROM leased_parking_space ORDER BY parking_space_id DESC";
        return getList(connection, sql);
    }
}

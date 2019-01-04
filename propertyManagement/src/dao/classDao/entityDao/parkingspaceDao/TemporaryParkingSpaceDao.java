package dao.classDao.entityDao.parkingspaceDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.parkingspace.TemporaryParkingSpace;

import java.sql.Connection;
import java.util.List;

/**
 * Created by your dad on 2019/1/4.
 */
public class TemporaryParkingSpaceDao extends JdbcDaoImpl<TemporaryParkingSpace> {
    private static Connection connection = JDBCUtil.getConnection();
    public TemporaryParkingSpaceDao(){
        init();
    }
    private static void init(){}
    public boolean addTemporaryParkingSpace(TemporaryParkingSpace temporaryParkingSpace) {
        String sql = "INSERT INTO temporary_parking_space (parking_space_id, community_id, description,parking_state) " +
                "values (?,?,?,?)";
        update(connection,sql,temporaryParkingSpace.getParking_space_id(), temporaryParkingSpace.getCommunity_id(), temporaryParkingSpace.getDescription(),temporaryParkingSpace.getParking_state());
        return true;
    }
    public TemporaryParkingSpace getTemporaryParkingSpace(int temporaryParkingSpaceId){
        String sql = "SELECT * " +
                "FROM temporary_parking_space where answer_id = ? ";
        return get(connection, sql, temporaryParkingSpaceId);
    }
    public boolean updateTemporaryParkingSpace(TemporaryParkingSpace temporaryParkingSpace){
        String sql = "UPDATE temporary_parking_space SET parking_state = ? where answer_id = ?";
        update(connection,sql,temporaryParkingSpace.getParking_state(), temporaryParkingSpace.getParking_space_id());
        return true;
    }
    public List<TemporaryParkingSpace> getAllTemporaryParkingSpace(){
        String sql = "SELECT * " +
                "FROM temporary_parking_space ORDER BY parking_space_id DESC";
        return getList(connection, sql);
    }
}

package dao.classDao.entityDao.parkingspaceDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.house.Community;
import model.entity.parkingspace.TemporaryParkingSpace;

import java.sql.Connection;
import java.util.List;

/**
 * Created by MoonBird on 2019/1/4.
 */
public class TemporaryParkingSpaceDao extends JdbcDaoImpl<TemporaryParkingSpace> {
    private static Connection connection = JDBCUtil.getConnection();
    public TemporaryParkingSpaceDao(){
        init();
    }
    private static void init(){}
    public boolean addTemporaryParkingSpace(TemporaryParkingSpace temporaryParkingSpace) {
        String sql = "INSERT INTO temporary_parking_space (community_id, description,parking_state) " +
                "values (?,?,?)";
        update(connection,sql,temporaryParkingSpace.getCommunity_id(), temporaryParkingSpace.getDescription(),temporaryParkingSpace.getParking_state());
        return true;
    }
    public TemporaryParkingSpace getTemporaryParkingSpace(int temporaryParkingSpaceId){
        String sql = "SELECT * " +
                "FROM temporary_parking_space where parking_space_id = ? ";
        return get(connection, sql, temporaryParkingSpaceId);
    }
    public boolean updateTemporaryParkingSpace(TemporaryParkingSpace temporaryParkingSpace){
        String sql = "UPDATE temporary_parking_space SET parking_state = ? where parking_space_id = ?";
        update(connection,sql,temporaryParkingSpace.getParking_state(), temporaryParkingSpace.getParking_space_id());
        return true;
    }
    public List<TemporaryParkingSpace> getAllTemporaryParkingSpace(){
        String sql = "SELECT * " +
                "FROM temporary_parking_space ORDER BY parking_space_id DESC";
        return getList(connection, sql);
    }

    public List<TemporaryParkingSpace> getTempParkingByCommunityAndState(Community community, int state){
        String sql = "SELECT * FROM temporary_parking_space where community_id = ? and parking_state = ?";
        return getList(connection, sql, community.getCommunityId(), state);
    }

    public List<TemporaryParkingSpace> getTempParkingSpacesByCommunity(Community community){
        String sql = "SELECT * FROM temporary_parking_space where community_id = ?";
        return getList(connection, sql, community.getCommunityId());
    }
}

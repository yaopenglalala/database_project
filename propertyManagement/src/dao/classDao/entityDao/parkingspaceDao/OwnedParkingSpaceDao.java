package dao.classDao.entityDao.parkingspaceDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.house.Community;
import model.entity.parkingspace.OwnedParkingSpace;

import java.sql.Connection;
import java.util.List;

/**
 * Created by your dad on 2019/1/4.
 */
public class OwnedParkingSpaceDao extends JdbcDaoImpl<OwnedParkingSpace> {
    private static Connection connection = JDBCUtil.getConnection();

    public OwnedParkingSpaceDao() {
        init();
    }

    private static void init() {
    }

    public boolean addOwnedParkingSpace(OwnedParkingSpace ownedParkingSpace) {
        String sql = "INSERT INTO owned_parking_space (community_id, description) " +
                "values (?,?)";
        update(connection, sql, ownedParkingSpace.getCommunity_id(), ownedParkingSpace.getDescription());
        return true;
    }
  /*  public OwnedParkingSpace getOwnedParkingSpace(int temporaryParkingSpaceId){
        String sql = "SELECT * " +
                "FROM temporary_parking_space where answer_id = ? ";
        return get(connection, sql, temporaryParkingSpaceId);
    }*/

    public List<OwnedParkingSpace> getOwnedParkingSpacesByCommunity(Community community) {
        String sql = "SELECT * FROM owned_parking_space where community = ?";
        return getList(connection, sql, community.getCommunityId());
    }

    public List<OwnedParkingSpace> getAllOwnedParkingSpace() {
        String sql = "SELECT * " +
                "FROM owned_parking_space ORDER BY parking_space_id DESC";
        return getList(connection, sql);
    }

    public OwnedParkingSpace getOwnedParkingSpaceBySpaceId(int spaceId){
        String sql = "SELECT * FROM owned_parking_space where parking_space_id = ?";
        return get(connection, sql, spaceId);
    }
}

package dao.classDao.entityDao.parkingspaceDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.house.Community;
import model.entity.parkingspace.LeasedParkingSpace;

import java.sql.Connection;
import java.util.List;

/**
 * Created by your dad on 2019/1/4.
 */
public class LeasedParkingSpaceDao extends JdbcDaoImpl<LeasedParkingSpace> {
    private static Connection connection = JDBCUtil.getConnection();

    public LeasedParkingSpaceDao() {
        init();
    }

    private static void init() {
    }

    public boolean addLeasedParkingSpace(LeasedParkingSpace leasedParkingSpace) {
        String sql = "INSERT INTO leased_parking_space (community_id, description,parking_state) " +
                "values (?,?,?)";
        update(connection, sql, leasedParkingSpace.getCommunity_id(), leasedParkingSpace.getDescription(),leasedParkingSpace.getParking_state());
        return true;
    }


    public boolean updateLeasedParkingSpace(LeasedParkingSpace leasedParkingSpace) {
        //  if (getInEquipByEquipId(indoorEquipment.getEquipment_id()) == null) return false;
        String sql = "UPDATE leased_parking_space SET parking_state=? where parking_space_id = ?";
        update(connection, sql, leasedParkingSpace.getParking_state(), leasedParkingSpace.getParking_space_id());
        return true;
    }
  /*  public OwnedParkingSpace getOwnedParkingSpace(int temporaryParkingSpaceId){
        String sql = "SELECT * " +
                "FROM temporary_parking_space where answer_id = ? ";
        return get(connection, sql, temporaryParkingSpaceId);
    }*/

    public boolean modifyLeasedParkingSpaceState(int spaceId, int state){
        String sql = "UPDATE leased_parking_space SET parking_state = ? where parking_space_id = ?";
        return update(connection, sql, state, spaceId) != 0;
    }

    public List<LeasedParkingSpace> getLeasedParkingByCommunityAndState(Community community, int state) {
        String sql = "SELECT * FROM leased_parking_space where community_id = ? and parking_state = ?";
        return getList(connection, sql, community.getCommunityId(), state);
    }

    public List<LeasedParkingSpace> getLeasedParkingSpacesByCommunity(Community community) {
        String sql = "SELECT * FROM leased_parking_space where community_id = ?";
        return getList(connection, sql, community.getCommunityId());
    }

    public LeasedParkingSpace getLeasedParkingSpaceBySpaceId(int spaceId) {
        String sql = "SELECT * FROM leased_parking_space where parking_space_id = ?";
        return get(connection, sql, spaceId);
    }

    public List<LeasedParkingSpace> getAllLeasedParkingSpace() {
        String sql = "SELECT * " +
                "FROM leased_parking_space ORDER BY parking_space_id DESC";
        return getList(connection, sql);
    }
}

package dao.classDao.relationDao.parkingDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.parking.LeasedParkingRecord;

import java.sql.Connection;
import java.util.List;

/**
 * Created by MoonBird on 2019/1/4.
 */
public class LeasedParkingRecordDao extends JdbcDaoImpl<LeasedParkingRecord> {
    private static Connection connection = JDBCUtil.getConnection();

    public LeasedParkingRecordDao(){
        init();
    }

    public boolean addRecord(LeasedParkingRecord record){
        String sql = "INSERT INTO leased_parking_record (parking_space_id, resident_id, start_time, end_time)" +
                " value (?, ?, ?, ?)";
        return update(connection, sql, record.getParking_space_id(),record.getResident_id(), record.getStart_time(), record.getEnd_time()) != 0;
    }

    public List<LeasedParkingRecord> getRecordsBySpaceId(int parkingSpaceId){
        String sql = "SELECT * FROM leased_parking_record where parking_space_id = ?";
        return getList(connection, sql, parkingSpaceId);
    }

    public List<LeasedParkingRecord> getRecordsByResidentId(int residentId){
        String sql = "SELECT * FROM leased_parking_record where resident_id = ?";
        return getList(connection, sql, residentId);
    }

    private static void init(){

    }
}

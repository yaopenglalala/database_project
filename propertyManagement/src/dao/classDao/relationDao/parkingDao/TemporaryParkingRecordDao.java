package dao.classDao.relationDao.parkingDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.parking.LeasedParkingRecord;
import model.relation.parking.TemporaryParkingRecord;

import java.sql.Connection;
import java.util.List;

/**
 * Created by MoonBird on 2019/1/4.
 */
public class TemporaryParkingRecordDao extends JdbcDaoImpl<TemporaryParkingRecord> {
    private static Connection connection = JDBCUtil.getConnection();

    public TemporaryParkingRecordDao(){
        init();
    }

    public boolean addRecord(TemporaryParkingRecord record){
        String sql = "INSERT INTO temp_parking_record (parking_space_id, car_id, start_time, end_time, cost)" +
                " value (?, ?, ?, ?, ?)";
        return update(connection, sql, record.getParking_space_id(),record.getCar_id(), record.getStart_time(), record.getEnd_time(), record.getCost()) != 0;
    }

    public List<TemporaryParkingRecord> getRecordsBySpaceId(int parkingSpaceId){
        String sql = "SELECT * FROM temp_parking_record where parking_space_id = ?";
        return getList(connection, sql, parkingSpaceId);
    }

    private static void init(){

    }
}

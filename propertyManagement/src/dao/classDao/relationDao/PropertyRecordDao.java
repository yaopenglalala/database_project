package dao.classDao.relationDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.PropertyRecord;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by MoonBird on 2019/1/4.
 */
public class PropertyRecordDao extends JdbcDaoImpl<PropertyRecord> {
    private static Connection connection = JDBCUtil.getConnection();

    public PropertyRecordDao(){
        init();
    }

    public boolean addPropertyRecord(PropertyRecord record){
        String sql = "INSERT INTO property_record (type, amount, description, time)" +
                " value (?, ?, ?, ?)";
        return update(connection, sql, record.getType(), record.getAmount(), record.getDescription(), record.getTime()) != 0;
    }

    public List<PropertyRecord> getRecordsByTime(Date startTime, Date endTime){
        String sql = "SELECT FROM property_record where time > ? and time < ?";
        return getList(connection, sql, startTime, endTime);
    }

    private static void init(){

    }
}

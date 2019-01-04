package dao.classDao.relationDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.ProperMonthRecord;
import model.relation.resident.ResidentCost;

import java.sql.Connection;
import java.util.List;

/**
 * Created by MoonBird on 2019/1/4.
 */
public class ProperMonthRecordDao extends JdbcDaoImpl<ProperMonthRecord> {
    private static Connection connection = JDBCUtil.getConnection();

    public ProperMonthRecordDao(){
        init();
    }

    public boolean addProperMonthRecord(ProperMonthRecord record){
        String sql = "INSERT INTO proper_month_record (year, month, type, amount)" +
                " value (?, ?, ?, ?)";
        return update(connection, sql, record.getYear(),record.getMonth(), record.getType(), record.getAmount()) != 0;
    }

    public List<ProperMonthRecord> getRecordsByYear(int year){
        String sql = "SELECT * FROM proper_month_record where year = ?";
        return getList(connection, sql, year);
    }

    public List<ProperMonthRecord> getRecordsByMonth(int month){
        String sql = "SELECT * FROM proper_month_record where month = ?";
        return getList(connection, sql, month);
    }

    public ProperMonthRecord getRecordByYearAndMonth(int year, int month){
        String sql = "SELECT * FROM proper_month_record where year = ? and month = ?";
        return get(connection, sql, year, month);
    }

    private static void init(){

    }
}

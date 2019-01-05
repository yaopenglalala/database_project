package dao.classDao.relationDao.buildingDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.building.HousePurchaseRecord;

import java.sql.Connection;
import java.util.List;

/**
 * Created by moonBird on 2019/1/4.
 */
public class HousePurchaseRecordDao extends JdbcDaoImpl<HousePurchaseRecord> {
    private static Connection connection = JDBCUtil.getConnection();

    public HousePurchaseRecordDao(){
        init();
    }

    public boolean addRecord(HousePurchaseRecord housePurchaseRecord){
        if (getRecordByHouseId(housePurchaseRecord.getHouse_id()) != null) return false;//房屋已经被购买
        String sql = "INSERT INTO house_purchase_record (house_id, resident_id, cost, time) " +
                " value (?,?,?,?)";
        update(connection, sql, housePurchaseRecord.getHouse_id(), housePurchaseRecord.getResident_id(), housePurchaseRecord.getCost(), housePurchaseRecord.getTime());
        return true;
    }

    public HousePurchaseRecord getRecordByHouseId(int houseId){
        String sql = "SELECT * FROM house_purchase_record where house_id = ?";
        return get(connection, sql, houseId);
    }

    public List<HousePurchaseRecord> getAll(){
        String sql = "SELECT * FROM house_purchase_record";
        return getList(connection, sql);
    }

    private static void init(){

    }
}

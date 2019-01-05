package dao.classDao.entityDao.houseDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.house.House;

import java.sql.Connection;
import java.util.List;

/**
 * Created by your dad on 2019/1/4.
 */
public class HouseDao extends JdbcDaoImpl<House> {
    private static Connection connection = JDBCUtil.getConnection();
    public HouseDao(){
        init();
    }
    private static void init() {

    }

    public boolean addHouse(House house) {
        String sql = "INSERT INTO house (building_id, name, size) " +
                "values (?,?,?)";
        update(connection,sql,house.getBuilding_id(), house.getName(),house.getSize());
        return true;
    }

    public List<House> getHousesByBuildingId(int building_id){
        String sql = "SELECT * FROM house where building_id = ?";
        return getList(connection, sql, building_id);
    }

    public List<House> getAllHouse(){
        String sql = "SELECT * " +
                "FROM house ORDER BY house_id DESC";
        return getList(connection, sql);
    }

    public House getHouseByHouseId(int houseId){
        String sql = "SELECT * FROM house where house_id = ?";
        return get(connection, sql, houseId);
    }
}

package dao.classDao.entityDao.houseDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.house.Community;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * Created by your dad on 2019/1/4.
 */
public class CommunityDao extends JdbcDaoImpl<Community> {
    private static Connection connection = JDBCUtil.getConnection();
    public CommunityDao(){
        init();
    }
    private static void init() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF not exists community\n" +
                            "(\n" +
                            "    community_id INT(32) PRIMARY KEY NOT NULL,\n" +
                            "    name VARCHAR(255),\n" +
                            "    longitude FLOAT(255,1),\n" +
                            "    latitude FLOAT(255,1)\n" +
                            ");"
            );
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addCommunity(Community community) {
        String sql = "INSERT INTO community (community_id, name, longitude, latitude) " +
                "values (?,?,?,?)";
        update(connection,sql,community.getCommunity_id(), community.getName(), community.getLongitude(),community.getLatitude());
        return true;
    }

    public List<Community> getAllCommunity(){
        String sql = "SELECT * " +
                "FROM community ORDER BY community_id DESC";
        return getList(connection, sql);
    }

}

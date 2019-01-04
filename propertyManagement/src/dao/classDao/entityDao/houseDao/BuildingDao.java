package dao.classDao.entityDao.houseDao;
import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.entity.house.Building;

import java.sql.Connection;
import java.util.List;

/**
 * Created by your dad on 2019/1/4.
 */
public class BuildingDao extends JdbcDaoImpl<Building> {
    private static Connection connection = JDBCUtil.getConnection();
    public BuildingDao(){
        init();
    }
    private static void init() {
      /*  try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF not exists building\n" +
                            "(\n" +
                            "    building_id INT(32) PRIMARY KEY NOT NULL,\n" +
                            "    community_id INT(32) NOT NULL,\n" +
                            "    name VARCHAR(255),\n" +
                            "    CONSTRAINT community_id FOREIGN KEY (community_id) REFERENCES community (community_id)\n" +
                            ");\n" +
                            "CREATE INDEX community_id ON building (community_id);"
            );
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public boolean addBuilding(Building building) {
        String sql = "INSERT INTO building (building_id, community_id,name) " +
                "values (?,?,?)";
        update(connection,sql,building.getBuilding_id(), building.getCommunity_id(), building.getName());
        return true;
    }

    public List<Building> getBuildingsByCommunityId(int communityId){
        String sql = "SELECT * FROM building where community_id = ?";
        return getList(connection, sql, communityId);
    }

    public List<Building> getAllBuildings(){
        String sql = "SELECT * " +
                "FROM building ORDER BY building_id DESC";
        return getList(connection, sql);
    }

}

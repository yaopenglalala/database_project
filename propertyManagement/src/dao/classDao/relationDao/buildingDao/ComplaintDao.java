package dao.classDao.relationDao.buildingDao;

import dao.JDBCUtil;
import dao.daoInterface.JdbcDaoImpl;
import model.relation.building.Complaint;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * Created by moonBird on 2019/1/4.
 */
public class ComplaintDao extends JdbcDaoImpl<Complaint> {
    private static Connection connection = JDBCUtil.getConnection();

    public ComplaintDao(){
        init();
    }

    public boolean addComplaint(Complaint complaint){
        String sql = "INSERT INTO complaint (house_id, type, description, process, date)" +
                "value (?,?,?,?,?)";
        update(connection, sql, complaint.getHouse_id(), complaint.getType(),
                complaint.getDescription(), complaint.getProcess(), complaint.getDate());

        return true;
    }

    public boolean modifyComplaint(Complaint complaint){
        String sql = "UPDATE complaint SET process = ? where feedback_id = ? ";//false 表示不存在这个投诉
        return update(connection, sql, complaint.getProcess(), complaint.getFeedback_id()) != 0;
    }

    public List<Complaint> getComplaintsByBuildingId(int building_id){
        String sql = "SELECT feedback_id, house_id, type, description, process, date FROM HouseService natural join complaint where building_id = ?";
        return getList(connection, sql, building_id);
    }

    public List<Complaint> getComplaintsByHouseId(int house_id){
        String sql = "SELECT * FROM complaint where house_id = ?";
        return getList(connection, sql, house_id);
    }

    public List<Complaint> getComplaintsByTime(Date start, Date end){
        String sql = "SELECT * FROM complaint where date > ? and date < ?";
        return getList(connection, sql, start, end);
    }

    public List<Complaint> getComplaintsByType(int type){
        String sql = "SELECT * FROM complaint where type = ?";
        return getList(connection, sql, type);
    }

    public List<Complaint> getAllComplaints(){
        String sql = "SELECT * FROM complaint";
        return getList(connection, sql);
    }

    public Complaint getComplaintByFBid(int feedback_id){
        String sql = "SELECT * FROM complaint where feedback_id = ?";
        return get(connection, sql, feedback_id);
    }

    private static void init(){

    }
}

package service;

import dao.classDao.relationDao.buildingDao.ComplaintDao;
import dao.classDao.relationDao.equipmentDao.EquipmentIssueDao;
import model.entity.equipment.IndoorEquipment;
import model.entity.house.Building;
import model.entity.house.House;
import model.relation.equipment.EquipmentIssue;
import model.relation.equipment.IndoorEquipRepair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedbackService {
    EquipmentIssueDao issueDao;
    ComplaintDao complaintDao;

    public FeedbackService(){
        issueDao = new EquipmentIssueDao();
        complaintDao = new ComplaintDao();
    }

    public List<EquipmentIssue> getIssuesByEquip(IndoorEquipment equipment){
        return issueDao.getIssuesByEquipId(equipment.getEquipment_id());
    }

    public List<EquipmentIssue> getIssuesByHouse(House house){
        return issueDao.getIssuesByHouseId(house.getHouse_id());
    }

    public List<EquipmentIssue> getIssuesByBuiding(Building building){
        return issueDao.getIssuesByBuildingId(building.getBuilding_id());
    }

    //还未处理的问题
    public List<EquipmentIssue> getNotSolvedIssues(Building building){
        return issueDao.getNotSolvedIssues(building.getBuilding_id());
    }

    //正在处理的问题
    public List<EquipmentIssue> getSolvingIssues(Building building){
        return getEquipmentIssues(building, 1);
    }

    //已经解决的问题
    public List<EquipmentIssue> getSolvedIssues(Building building){
        return getEquipmentIssues(building, 0);
    }

    private List<EquipmentIssue> getEquipmentIssues(Building building, int i) {
        EquipmentService equipmentService = new EquipmentService();
        ArrayList<EquipmentIssue> res = new ArrayList<>();
        List<EquipmentIssue> solvingIssues = issueDao.getSolvingIssues(building.getBuilding_id());
        for (EquipmentIssue issue : solvingIssues) {
            if (equipmentService.getInEquipRepairByRepairId(issue.getRepair_id()).getState()
                    == i) res.add(issue);
        }
        return res;
    }

    public boolean addInEquipIssue(IndoorEquipment equipment, House house, int type, String description){
        EquipmentIssue issue = new EquipmentIssue();
        issue.setEquipment_id(equipment.getEquipment_id());
        issue.setHouse_id(house.getHouse_id());
        issue.setRepair_id(null);
        issue.setType(type);
        issue.setDescription(description);
        issue.setTime(new Date());
        return issueDao.addIssue(issue);
    }

    /**
     * 修改保修的设备的维修状态
     * @param issue
     * @param repairId
     * @return 0表示成功，1表示该次维修不存在
     */
    public int modifyIssueRepairId(EquipmentIssue issue, int repairId){
        EquipmentService equipmentService = new EquipmentService();
        if (equipmentService.getInEquipRepairByRepairId(repairId) == null) return 1;
        else {
            issueDao.modifyRepairId(issue.getFeedback_id(),repairId);
            return 0;
        }
    }


//    public int addComplaint(House house, ){
//
//    }
}

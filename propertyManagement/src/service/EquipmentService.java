package service;

import dao.classDao.entityDao.equipmentDao.IndoorEquipmentDao;
import dao.classDao.entityDao.equipmentDao.OutdoorEquipmentDao;
import dao.classDao.relationDao.equipmentDao.*;
import model.entity.equipment.IndoorEquipment;
import model.entity.equipment.OutdoorEquipment;
import model.entity.house.Building;
import model.entity.house.Community;
import model.relation.equipment.IndoorEquipCheck;
import model.relation.equipment.IndoorEquipRepair;
import model.relation.equipment.OutdoorEquipCheck;
import model.relation.equipment.OutdoorEquipRepair;

import java.util.Date;
import java.util.List;

public class EquipmentService {
    private IndoorEquipmentDao indoorDao;
    private OutdoorEquipmentDao outdoorDao;

    private InEquipCheckDao inCheckDao;
    private OutEquipCheckDao outCheckDao;

    private InEquipRepairDao inRepairDao;
    private OutEquipRepairDao outRepairDao;

    private EquipmentIssueDao issueDao;

    public EquipmentService (){
        indoorDao = new IndoorEquipmentDao();
        outdoorDao = new OutdoorEquipmentDao();

        inCheckDao = new InEquipCheckDao();
        outCheckDao = new OutEquipCheckDao();

        inRepairDao = new InEquipRepairDao();
        outRepairDao = new OutEquipRepairDao();

        issueDao = new EquipmentIssueDao();
    }

    public List<IndoorEquipment> getAllIndoorEquipByBuilding(Building building){
        return indoorDao.getInEquipByBuilding(building);
    }

    public List<OutdoorEquipment> getAllOutdoorEquipByCommunity(Community community){
        return outdoorDao.getOutEquipByCommunity(community);
    }

    public List<IndoorEquipment> getInEquipByBuildingAndState(Building building, int state){
        return indoorDao.getInEquipByState(building,state);
    }

    public List<OutdoorEquipment> getOutEquipByCommunityAndState(Community community, int state){
        return outdoorDao.getOutEquipByState(community, state);
    }

    public List<IndoorEquipCheck> getInEquipCheck(IndoorEquipment equipment){
        return inCheckDao.getChecksByEquipId(equipment.getEquipment_id());
    }

    public List<OutdoorEquipCheck> getOutEquipCheck(OutdoorEquipment equipment){
        return outCheckDao.getChecksByEquipId(equipment.getEquipment_id());
    }

    public List<IndoorEquipRepair> getInEquipRepair(IndoorEquipment equipment){
        return inRepairDao.getRepairsByEquimentId(equipment.getEquipment_id());
    }

    public List<OutdoorEquipRepair> getOutEquipRepair(OutdoorEquipment outdoorEquipment){
        return outRepairDao.getRepairsByEquimentId(outdoorEquipment.getEquipment_id());
    }

    public int addInEquipCheck(IndoorEquipment equipment, int state){
        if (indoorDao.getInEquipByEquipId(equipment.getBuilding_id()) == null) return 1;
        IndoorEquipCheck check = new IndoorEquipCheck();
        check.setEquipment_id(equipment.getEquipment_id());
        check.setState(state);
        check.setTime(new Date());
        inCheckDao.addCheck(check);
        return 0;
    }

    public int addOutEquipCheck(OutdoorEquipment equipment, int state){
        if (outdoorDao.getOutEquipByEquipId(equipment.getEquipment_id()) == null) return 1;
        OutdoorEquipCheck check = new OutdoorEquipCheck();
        check.setEquipment_id(equipment.getEquipment_id());
        check.setState(state);
        check.setTime(new Date());
        outCheckDao.addCheck(check);
        return 0;
    }

    public int addInEquipRepair(IndoorEquipment equipment, float cost){
        if (indoorDao.getInEquipByEquipId(equipment.getEquipment_id()) == null) return 1;
        IndoorEquipRepair repair = new IndoorEquipRepair();
        repair.setEquipment_id(equipment.getEquipment_id());
        repair.setCost(cost);
        repair.setState(1);
        repair.setTime(new Date());
        inRepairDao.addRepair(repair);
        return 0;
    }

    public int addInEquipRepair(OutdoorEquipment equipment, float cost){
        if (outdoorDao.getOutEquipByEquipId(equipment.getEquipment_id()) == null) return 1;
        OutdoorEquipRepair repair = new OutdoorEquipRepair();
        repair.setEquipment_id(equipment.getEquipment_id());
        repair.setCost(cost);
        repair.setState(1);
        repair.setTime(new Date());
        outRepairDao.addRepair(repair);
        return 0;
    }
    
    public boolean modifyInEquipRepairState(IndoorEquipRepair repair, int state){
        return inRepairDao.modifyRepairState(repair.getRepair_id(), state);
    }

    public boolean modifyOutEquipRepairState(OutdoorEquipRepair repair, int state){
        return outRepairDao.modifyRepairState(repair.getRepair_id(), state);
    }
}

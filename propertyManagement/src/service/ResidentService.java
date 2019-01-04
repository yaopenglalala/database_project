package service;

import dao.classDao.entityDao.ResidentDao;
import dao.classDao.relationDao.buildingDao.HousePurchaseRecordDao;
import dao.classDao.relationDao.residentDao.ResidentCostDao;
import model.entity.Resident;
import model.entity.house.House;
import model.relation.building.HousePurchaseRecord;
import model.relation.resident.ResidentCost;

import java.util.Date;
import java.util.List;

public class ResidentService {
    private ResidentDao residentDao;
    private HousePurchaseRecordDao housePurchaseRecordDao;
    private ResidentCostDao residentCostDao;


    public ResidentService(){
        residentDao = new ResidentDao();
        housePurchaseRecordDao = new HousePurchaseRecordDao();
        residentCostDao = new ResidentCostDao();
    }

    public List<Resident> getAllResidents(){
        return residentDao.getAllResident();
    }

    public Resident getResidentByIdentityCard(String identity){
        return residentDao.getResidentByIdentity(identity);
    }

    public Resident getResidentByResidentId(int residentId){
        return residentDao.getResidentByResidentId(residentId);
    }

    public Resident getHouseOwner(House house){
        HousePurchaseRecord housePurchaseRecord = housePurchaseRecordDao.getRecordByHouseId(house.getHouse_id());
        if (housePurchaseRecord == null) return null;
        return residentDao.getResidentByResidentId(housePurchaseRecord.getResident_id());
    }

    public boolean pay(int costId){
        return residentCostDao.modifyState(costId, 0);
    }

    /**
     *
     * @param residentId
     * @param description
     * @param cost
     * @return false 表示不存在该用户
     */
    public boolean addCost(int residentId, String description, float cost){
        if (getResidentByResidentId(residentId) == null) return false;
        ResidentCost residentCost = new ResidentCost();
        residentCost.setResident_id(residentId);
        residentCost.setDescription(description);
        residentCost.setCost(cost);
        residentCost.setTime(new Date());
        residentCost.setState(1);
        residentCostDao.addCost(residentCost);
        return true;
    }
}

package service;

import dao.classDao.relationDao.ProperMonthRecordDao;
import dao.classDao.relationDao.PropertyRecordDao;
import dao.classDao.relationDao.equipmentDao.InEquipRepairDao;
import dao.classDao.relationDao.equipmentDao.OutEquipRepairDao;
import dao.classDao.relationDao.parkingDao.TemporaryParkingRecordDao;
import dao.classDao.relationDao.residentDao.ResidentCostDao;
import model.relation.PropertyRecord;
import model.relation.equipment.IndoorEquipRepair;
import model.relation.equipment.OutdoorEquipRepair;
import model.relation.parking.TemporaryParkingRecord;
import model.relation.resident.ResidentCost;

import java.util.List;

/**
 * Created by your dad on 2019/1/5.
 */
public class PaymentService {
    TemporaryParkingRecordDao temporaryParkingRecordDao;
    ResidentCostDao      residentCostDao;
    InEquipRepairDao    indoorEquipRepairDao;
    OutEquipRepairDao   outEquipRepairDao;
    PropertyRecordDao    propertyRecordDao;
    ProperMonthRecordDao properMonthRecordDao;
    public PaymentService(){
        temporaryParkingRecordDao = new TemporaryParkingRecordDao();
        residentCostDao =new ResidentCostDao();
        indoorEquipRepairDao = new InEquipRepairDao();
        outEquipRepairDao=new OutEquipRepairDao();
        propertyRecordDao = new PropertyRecordDao();
        properMonthRecordDao=new ProperMonthRecordDao();
    }
    //用户付款状态调整
    public boolean pay(int costId){
        return residentCostDao.modifyState(costId, 0);
    }

    public List<TemporaryParkingRecord> getTemParkingRecordCost(){
            return temporaryParkingRecordDao.getRecordsByCostNoNull();
    }

    public List<ResidentCost> getResidentCost(){
        return residentCostDao.getAllCosts();
    }
    public List<IndoorEquipRepair> getInRepairCost(){
        return indoorEquipRepairDao.getAllRepairs();
    }
    public List<OutdoorEquipRepair> getOutRepairCost(){
        return outEquipRepairDao.getAllRepairs();
    }
   public List<PropertyRecord> getPropertyRecordCost(){
       return propertyRecordDao.getAllRecords();
   }
   public List<ResidentCost> getResidentCostByResidentId(int id){
       return residentCostDao.getCostsByResidentId(id);
   }
   public void renewMoney(){

   }
}

package service;

import dao.classDao.relationDao.ProperMonthRecordDao;
import dao.classDao.relationDao.PropertyRecordDao;
import dao.classDao.relationDao.equipmentDao.InEquipRepairDao;
import dao.classDao.relationDao.equipmentDao.OutEquipRepairDao;
import dao.classDao.relationDao.parkingDao.TemporaryParkingRecordDao;
import dao.classDao.relationDao.residentDao.ResidentCostDao;
import model.entity.house.Building;
import model.entity.house.House;
import model.relation.ProperMonthRecord;
import model.relation.PropertyRecord;
import model.relation.building.HousePurchaseRecord;
import model.relation.equipment.IndoorEquipRepair;
import model.relation.equipment.OutdoorEquipRepair;
import model.relation.parking.LeasedParkingRecord;
import model.relation.parking.OwnedParkingRecord;
import model.relation.parking.TemporaryParkingRecord;
import model.relation.resident.ResidentCost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by your dad on 2019/1/5.
 */
public class PaymentService {
    private int[] parkingRentCost = {200, 250, 300};
    private float[] houseManagingCost = {1.0f, 1.5f, 2.0f};
    private int parkingManagingCost = 50;

    private TemporaryParkingRecordDao temporaryParkingRecordDao;
    private ResidentCostDao residentCostDao;
    private InEquipRepairDao indoorEquipRepairDao;
    private OutEquipRepairDao outEquipRepairDao;
    private PropertyRecordDao propertyRecordDao;
    private ProperMonthRecordDao properMonthRecordDao;

    public PaymentService() {
        temporaryParkingRecordDao = new TemporaryParkingRecordDao();
        residentCostDao = new ResidentCostDao();
        indoorEquipRepairDao = new InEquipRepairDao();
        outEquipRepairDao = new OutEquipRepairDao();
        propertyRecordDao = new PropertyRecordDao();
        properMonthRecordDao = new ProperMonthRecordDao();
    }

    //用户付款状态调整
    public boolean pay(int costId) {
        return residentCostDao.modifyState(costId, 0);
    }

    public List<TemporaryParkingRecord> getTemParkingRecordCost() {
        return temporaryParkingRecordDao.getRecordsByCostNoNull();
    }

    public List<ResidentCost> getResidentCost() {
        return residentCostDao.getAllCosts();
    }

    public List<IndoorEquipRepair> getInRepairCost() {
        return indoorEquipRepairDao.getAllRepairs();
    }

    public List<OutdoorEquipRepair> getOutRepairCost() {
        return outEquipRepairDao.getAllRepairs();
    }

    public List<PropertyRecord> getPropertyRecordCost() {
        return propertyRecordDao.getAllRecords();
    }

    public List<ResidentCost> getResidentCostByResidentId(int id) {
        return residentCostDao.getCostsByResidentId(id);
    }

    public ProperMonthRecord monthProperty() throws ParseException {
        int firstMonthFlag = 0;
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.MONTH) == 0) firstMonthFlag = 1;

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

        int lastMonthYear = currentYear - firstMonthFlag;
        System.out.println(lastMonthYear);
        int lastMonthMonth = currentMonth - 1;
        if (lastMonthMonth == 0) lastMonthMonth = 12;
        System.out.println(lastMonthMonth);

        calendar.set(currentYear, currentYear, 1);
        Date currentMonthStart = calendar.getTime();
        calendar.set(currentYear, currentMonth, 30);
        Date currentMonthEnd = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date lastMonthStart = sdf.parse(lastMonthYear + "-"  + lastMonthMonth + "-" + "01");
        System.out.println(lastMonthStart);
        Date lastMonthEnd = sdf.parse(lastMonthYear + "-"  + lastMonthMonth + "-" + "30");
        System.out.println(lastMonthEnd);


        ProperMonthRecord monthRecord = properMonthRecordDao.getRecordByYearAndMonth(lastMonthYear, lastMonthMonth);
        if (monthRecord != null) return monthRecord;

        ParkingService parkingService = new ParkingService();
        ResidentService residentService = new ResidentService();
        HouseService houseService = new HouseService();
        EquipmentService equipmentService = new EquipmentService();
        /*
          向resident_cost添加本月应缴费用
         */
        //租借车位费与租借车位管理费
        List<LeasedParkingRecord> leasedParkingRecords = parkingService.getValidRentRecord(currentMonthStart);
        for (LeasedParkingRecord record : leasedParkingRecords){
            residentService.addCost(record.getResident_id(), "Rent parking space cost.",
                    parkingRentCost[parkingService.getParkingSpaceBySpaceId(record.getParking_space_id()).getCommunity_id()]);
            residentService.addCost(record.getResident_id(), "Rent parking managing cost", parkingManagingCost);
        }

        //购买车位管理费
        List<OwnedParkingRecord> ownedParkingRecords = parkingService.getOwnedParkingRecord();
        for (OwnedParkingRecord record : ownedParkingRecords){
            residentService.addCost(record.getResident_id(), "Purchased parking space managing cost.", parkingManagingCost);
        }

        //物业费
        List<HousePurchaseRecord> housePurchaseRecords = houseService.getAllPurchasedRecord();
        for (HousePurchaseRecord record : housePurchaseRecords){
            House house = houseService.getHouseByHouseId(record.getHouse_id());
            Building building = houseService.getBuildingByBuildingId(house.getBuilding_id());
            residentService.addCost(record.getResident_id(), "Purchased house managing cost.",
                    house.getSize() * houseManagingCost[building.getCommunity_id()]);
        }

        //计算物业收支
            //收入
                //来自用户的收入
        float income = 0;
        List<ResidentCost> residentCosts = residentService.getCostsBetweenTime(lastMonthStart, lastMonthEnd);
        for (ResidentCost cost : residentCosts) {
            income += cost.getCost();
        }
            //支出
                //维修费支出
        float expenses = 0;
        List<IndoorEquipRepair> inRepairs = equipmentService.getInRepairsByTime(lastMonthStart, lastMonthEnd);
        for (IndoorEquipRepair repair : inRepairs){
            expenses += repair.getCost();
        }

        List<OutdoorEquipRepair> outRepairs = equipmentService.getOutRepairsByTime(lastMonthStart, lastMonthEnd);
        for (OutdoorEquipRepair repair : outRepairs){
            expenses += repair.getCost();
        }

        //其他收入与其他支出
        List<PropertyRecord> otherIncomes = propertyRecordDao.getRecordsByTime(lastMonthStart, lastMonthEnd);
        for (PropertyRecord record : otherIncomes){
            if (record.getType() == 0) {
                income += record.getAmount();
            } else if (record.getType() == 1){
                expenses += record.getAmount();
            }
        }

        float properMonthRes = income - expenses;
        monthRecord = new ProperMonthRecord();
        monthRecord.setYear(lastMonthYear);
        monthRecord.setMonth(lastMonthMonth);
        monthRecord.setAmount(Math.abs(properMonthRes));

        if (properMonthRes > 0) {
            monthRecord.setType(0);
            properMonthRecordDao.addProperMonthRecord(monthRecord);
        } else if (properMonthRes <= 0){
            monthRecord.setType(1);
            properMonthRecordDao.addProperMonthRecord(monthRecord);
        }
        return monthRecord;
    }
}

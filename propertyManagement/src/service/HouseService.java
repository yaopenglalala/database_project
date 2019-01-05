package service;

import dao.classDao.entityDao.houseDao.BuildingDao;
import dao.classDao.entityDao.houseDao.CommunityDao;
import dao.classDao.entityDao.houseDao.HouseDao;
import dao.classDao.relationDao.buildingDao.HousePurchaseRecordDao;
import model.entity.Resident;
import model.entity.house.Building;
import model.entity.house.Community;
import model.entity.house.House;
import model.relation.building.HousePurchaseRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HouseService {
    private CommunityDao communityDao;
    private BuildingDao buildingDao;
    private HouseDao houseDao;

    private HousePurchaseRecordDao housePurchaseRecordDao;

    public HouseService(){
        communityDao = new CommunityDao();
        buildingDao = new BuildingDao();
        houseDao = new HouseDao();
        housePurchaseRecordDao = new HousePurchaseRecordDao();
    }

    public List<Community> getCommunitys(){
        return communityDao.getAllCommunity();
    }

    public List<Building> getBuildings(Community community){
        return buildingDao.getBuildingsByCommunityId(community.getCommunityId());
    }

    public List<House> getHouses(Building building){
        return houseDao.getHousesByBuildingId(building.getBuilding_id());
    }

    public List<House> getEmptyHousesByCommunity(Community community){
        List<House> res = new ArrayList<>();
        List<Building> buildings = getBuildings(community);
        for (Building building : buildings) {
            res.addAll(getEmptyHousesByBuilding(building));
        }
        return res;
    }

    public List<House> getEmptyHousesByBuilding(Building building){
        List<House> res = new ArrayList<>();
        List<House> houses = getHouses(building);
        for (House house : houses){
            if (housePurchaseRecordDao.getRecordByHouseId(house.getHouse_id()) == null)
                res.add(house);
        }
        return res;
    }

    /**
     *
     * @param residentId
     * @param house
     * @return 1表示住户不存在，2表示房屋已出售，0表示购买操作成功
     */
    public int housePurchase(int residentId, House house, float cost){
        ResidentService residentService = new ResidentService();
        Resident resident = residentService.getResidentByResidentId(residentId);
        if (resident == null) return 1;
        if (housePurchaseRecordDao.getRecordByHouseId(house.getHouse_id()) != null) return 2;
        HousePurchaseRecord purchaseRecord = new HousePurchaseRecord();
        purchaseRecord.setHouse_id(house.getHouse_id());
        purchaseRecord.setResident_id(residentId);
        purchaseRecord.setCost(cost);
        purchaseRecord.setTime(new Date());
        housePurchaseRecordDao.addRecord(purchaseRecord);
        residentService.addCost(residentId, "Purchase house", cost);
        return 0;
    }
}

package service;

import dao.classDao.entityDao.parkingspaceDao.LeasedParkingSpaceDao;
import dao.classDao.entityDao.parkingspaceDao.OwnedParkingSpaceDao;
import dao.classDao.entityDao.parkingspaceDao.TemporaryParkingSpaceDao;
import dao.classDao.relationDao.parkingDao.LeasedParkingRecordDao;
import dao.classDao.relationDao.parkingDao.OwnedParkingRecordDao;
import dao.classDao.relationDao.parkingDao.TemporaryParkingRecordDao;
import model.entity.Resident;
import model.entity.house.Community;
import model.entity.parkingspace.LeasedParkingSpace;
import model.entity.parkingspace.OwnedParkingSpace;
import model.entity.parkingspace.TemporaryParkingSpace;
import model.relation.parking.LeasedParkingRecord;
import model.relation.parking.OwnedParkingRecord;
import model.relation.parking.TemporaryParkingRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParkingService {
    LeasedParkingSpaceDao leasedDao;
    OwnedParkingSpaceDao ownedDao;
    TemporaryParkingSpaceDao tempDao;

    LeasedParkingRecordDao leasedRecordDao;
    OwnedParkingRecordDao ownedRecordDao;
    TemporaryParkingRecordDao tempRecordDao;

    public ParkingService(){
        leasedDao = new LeasedParkingSpaceDao();
        leasedRecordDao = new LeasedParkingRecordDao();

        ownedDao = new OwnedParkingSpaceDao();
        ownedRecordDao = new OwnedParkingRecordDao();

        tempDao = new TemporaryParkingSpaceDao();
        tempRecordDao = new TemporaryParkingRecordDao();
    }

    public List<TemporaryParkingSpace> getAllTempPakingSpaces(Community community){
        return tempDao.getTempParkingSpacesByCommunity(community);
    }

    public List<LeasedParkingSpace> getAllLeasedParkingSpaces(Community community){
        return leasedDao.getLeasedParkingSpacesByCommunity(community);
    }

    public List<OwnedParkingSpace> getAllOwnedParkingSpace(Community community){
        return ownedDao.getOwnedParkingSpacesByCommunity(community);
    }

    public List<TemporaryParkingSpace> getEmptyTempParkingSpaces(Community community){
        return tempDao.getTempParkingByCommunityAndState(community, 0);
    }

    public List<LeasedParkingSpace> getEmptyLeasedParkingSpaces(Community community){
        return leasedDao.getLeasedParkingByCommunityAndState(community, 0);
    }

    public List<OwnedParkingSpace> getEmptyOwnedParkingSpaces(Community community){
        List<OwnedParkingSpace> res = new ArrayList<>();
        List<OwnedParkingSpace> allSpaces = ownedDao.getOwnedParkingSpacesByCommunity(community);
        for (OwnedParkingSpace space : allSpaces){
            if (ownedRecordDao.getRecordBySpaceId(space.getParking_space_id()) == null )
                res.add(space);
        }
        return res;
    }

    /**
     * 用于模拟临时停车（修改车位状态）
     * @param space
     * @return 0表示操作完成，1表示已有人使用，2表示没有该车位
     */
    public int tempParking(TemporaryParkingSpace space, String carId){
        space = tempDao.getTemporaryParkingSpace(space.getParking_space_id());
        if (space == null) return 2;
        else if (space.getParking_state() == 1) return 1;
        else {
            space.setParking_state(1);
            tempDao.updateTemporaryParkingSpace(space);
            TemporaryParkingRecord record = new TemporaryParkingRecord();
            record.setParking_space_id(space.getParking_space_id());
            record.setCar_id(carId);
            record.setStart_time(new Date());
            tempRecordDao.addRecord(record);
        }
        return 0;
    }

    /**
     * 车位离开
     * @param space
     * @param cost
     * @return 0表示操作完成，1表示没有使用，2表示没有该车位
     */
    public int tempLeaving(TemporaryParkingSpace space, float cost){
        space = tempDao.getTemporaryParkingSpace(space.getParking_space_id());
        if (space == null) return 2;
        else if (space.getParking_state() == 0) return 1;
        else {
            space.setParking_state(0);
            tempDao.updateTemporaryParkingSpace(space);
            TemporaryParkingRecord record = tempRecordDao.getRecordLatest(space.getParking_space_id());
            record.setParking_space_id(space.getParking_space_id());
            record.setEnd_time(new Date());
            record.setCost(cost);
            tempRecordDao.updateRecord(record);
        }
        return 0;
    }

    /**
     * 用于添加车位租赁信息
     * @param resident
     * @param parkingSpace
     * @return 0表示操作完成，1表示已有人使用，2表示没有该车位
     */
    public int rentParkingSpace(Resident resident, LeasedParkingSpace parkingSpace, Date startTime, Date endTime){
        parkingSpace = leasedDao.getLeasedParkingSpaceBySpaceId(parkingSpace.getParking_space_id());
        if (parkingSpace == null) return 2;
        if (parkingSpace.getParking_state() != 0){
            return 1;
        } else {
            leasedDao.modifyLeasedParkingSpaceState(parkingSpace.getParking_space_id(), 1);
            LeasedParkingRecord record = new LeasedParkingRecord();
            record.setResident_id(resident.getResident_id());
            record.setParking_space_id(parkingSpace.getParking_space_id());
            record.setStart_time(startTime);
            record.setEnd_time(endTime);
            leasedRecordDao.addRecord(record);
            return 0;
        }
    }

    /**
     *
     * @param resident
     * @param space
     * @param cost
     * @return 0表示操作完成，1表示已有人使用，2表示没有该车位
     */
    public int purchaseParkingSpace(Resident resident, OwnedParkingSpace space, float cost){
        if (ownedDao.getOwnedParkingSpaceBySpaceId(space.getParking_space_id()) == null) return 2;
        else if (ownedRecordDao.getRecordBySpaceId(space.getParking_space_id()) != null) return 1;
        else {
            OwnedParkingRecord record = new OwnedParkingRecord();
            ResidentService residentService = new ResidentService();
            residentService.addCost(resident.getResident_id(),"Purchase parking space.", cost);
            record.setResident_id(resident.getResident_id());
            record.setParking_space_id(space.getParking_space_id());
            record.setCost(cost);
            record.setDate(new Date());
            ownedRecordDao.addRecord(record);
            return 1;
        }
    }
}

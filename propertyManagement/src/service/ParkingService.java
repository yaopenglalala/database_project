package service;

import dao.classDao.entityDao.parkingspaceDao.LeasedParkingSpaceDao;
import dao.classDao.entityDao.parkingspaceDao.OwnedParkingSpaceDao;
import dao.classDao.entityDao.parkingspaceDao.TemporaryParkingSpaceDao;
import dao.classDao.relationDao.parkingDao.LeasedParkingRecordDao;
import dao.classDao.relationDao.parkingDao.OwnedParkingRecordDao;
import dao.classDao.relationDao.parkingDao.TemporaryParkingRecordDao;
import model.entity.house.Community;
import model.entity.parkingspace.LeasedParkingSpace;
import model.entity.parkingspace.OwnedParkingSpace;
import model.entity.parkingspace.TemporaryParkingSpace;

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
        List<OwnedParkingSpace> allSpaces = ownedDao.getOwnedParkingSpacesByCommunity(community);
        for (OwnedParkingSpace space : allSpaces){
            if (ownedRecordDao.getRecordBySpaceId(space.getParking_space_id()) != null )
                allSpaces.remove(space);
        }
        return allSpaces;
    }


}

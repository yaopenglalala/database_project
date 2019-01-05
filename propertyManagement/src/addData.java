import dao.classDao.entityDao.equipmentDao.IndoorEquipmentDao;

/**
 * Created by your dad on 2019/1/5.
 */
public class addData {
    public static void main(String[] args) {
      /*  HouseDao houseDao =new HouseDao();
        for(int i=0;i<15;i++){
            for(int j=0;j<10;j++){
                House house =new House();
                house.setBuilding_id(i);
                house.setName(j/5+"0"+j%5);
                house.setSize(40+j*5);
                houseDao.addHouse(house);
            }
        }*/
   /*   LeasedParkingSpaceDao leasedParkingSpaceDao = new LeasedParkingSpaceDao();
        OwnedParkingSpaceDao ownedParkingSpaceDao = new OwnedParkingSpaceDao();
        TemporaryParkingSpaceDao temporaryParkingSpaceDao = new TemporaryParkingSpaceDao();
        for (int i = 0; i < 3; i++) {
            LeasedParkingSpace leasedParkingSpace = new LeasedParkingSpace();
            OwnedParkingSpace ownedParkingSpace = new OwnedParkingSpace();
            TemporaryParkingSpace temporaryParkingSpace = new TemporaryParkingSpace();
            for (int j = 0; j < 10; j++) {
                String community = "";
                switch (i) {
                    case 0:
                        community = "A";
                        break;
                    case 1:
                        community = "B";
                        break;

                    case 2:
                        community = "C";
                        break;
                }
                leasedParkingSpace.setCommunity_id(i);
                leasedParkingSpace.setDescription("第"+community+"社区 第" + (j + 1) + "租赁车位");
                ownedParkingSpace.setCommunity_id(i);
                ownedParkingSpace.setDescription("第"+community+"社区 第"  + (j + 1) + "购买车位");
                temporaryParkingSpace.setCommunity_id(i);
                temporaryParkingSpace.setDescription("第"+community+"社区 第"  + (j + 1) + "临时车位");
                leasedParkingSpaceDao.addLeasedParkingSpace(leasedParkingSpace);
                ownedParkingSpaceDao.addOwnedParkingSpace(ownedParkingSpace);
                temporaryParkingSpaceDao.addTemporaryParkingSpace(temporaryParkingSpace);
            }
        }
*/
        IndoorEquipmentDao indoorEquipmentDao=new IndoorEquipmentDao();
     /*   OutdoorEquipmentDao outdoorEquipmentDao=new OutdoorEquipmentDao();

                  for(int i=0;i<3;i++){
                      String community = "";
                      switch (i) {
                          case 0:
                              community = "A";
                              break;
                          case 1:
                              community = "B";
                              break;

                          case 2:
                              community = "C";
                              break;
                      }
                      OutdoorEquipment outdoorEquipment = new OutdoorEquipment();
                      for(int j=0;j<10;j++) {
                          outdoorEquipment.setCommunity_id(i);
                          outdoorEquipment.setType("第"+community+"社区 第"+j+"设备");
                          outdoorEquipment.setDescription("没啥可说的设备，made in china");
                          outdoorEquipmentDao.addOutdoorEquipment(outdoorEquipment);
                      }
                  }*/
                /*  for(int i=0;i<15;i++){
                      IndoorEquipment indoorEquipment=new IndoorEquipment();

                      for(int j=0;j<5;j++){
                          indoorEquipment.setBuilding_id(i);
                          indoorEquipment.setType("第"+j+"设备");
                          indoorEquipment.setDescription("没啥可说的设备，made in china");
                          indoorEquipmentDao.addIndoorEquipment(indoorEquipment);
                      }
                  }*/

    }

}

import model.entity.Resident;
import model.entity.house.Building;
import model.entity.house.Community;
import model.entity.house.House;
import model.entity.parkingspace.LeasedParkingSpace;
import model.entity.parkingspace.OwnedParkingSpace;
import model.entity.parkingspace.TemporaryParkingSpace;
import model.relation.parking.TemporaryParkingRecord;
import model.relation.resident.ResidentCost;
import service.HouseService;
import service.ParkingService;
import service.PaymentService;
import service.ResidentService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by your dad on 2019/1/4.
 */
public class UI {
    public static void main(String[] args) {
        System.out.println("welcome to Community System");
        while (true) {
//0
            while (true) {
                floor_1();
                String input = Input();
                if (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4"))) {
                    System.out.println("已经最上层");
                    break;
                }
                if (input.equals("1")) {
//1
                    while (true) {
                        floor_2();
                        input = Input();
                        if (!(input.equals("1") || input.equals("2"))) {
                            System.out.println("已返回，请重新输入");
                            break;
                        }
                        if (input.equals("1")) {
                            HouseService houseService = new HouseService();
                            List<Community> communities = houseService.getCommunitys();
                            System.out.println("小区编号  " + "小区名称");
                            for (int i = 0; i < communities.size(); i++) {
                                System.out.println((i + 1) + "    " + communities.get(i).getName());
                            }
                            /*coding
                              List<>
                              打印
                              1->小区名称
                              2->...
                              完成
                             */
//1.1
                            while (true) {
                                System.out.println("请输入小区编号");
                                int inputInteger = Integer.parseInt(Input());
                                if (!(inputInteger >= 1 && inputInteger <= communities.size())) {
                                    //     System.out.println("已返回，请重新输入");
                                    break;
                                }
                                List<Building> buildings = houseService.getBuildings(communities.get(inputInteger - 1));
                                System.out.println("楼编号  " + "楼名称");
                                for (int i = 0; i < buildings.size(); i++) {
                                    System.out.println((i + 1) + "    " + buildings.get(i).getName());
                                }
                                /*coding
                              List<>
                              打印
                              1->楼宇名称
                              2->...
                              完成
                            */
                                while (true) {
                                    System.out.println("请输入楼编号");
                                    inputInteger = Integer.parseInt(Input());
                                    if (!(inputInteger >= 1 && inputInteger <= buildings.size())) {
                                        //   System.out.println("已返回，请重新输入");
                                        break;
                                    }
                                    List<House> houses = houseService.getEmptyHousesByBuilding(buildings.get(inputInteger - 1));
                                    System.out.println("房子编号  " + "房子名称  " + "房子size");
                                    for (int i = 0; i < houses.size(); i++) {
                                        System.out.println((i + 1) + "    " + houses.get(i).getName() + "  " + houses.get(i).getSize());
                                    }
                                     /*coding
                                     List<>
                                    打印
                                    1->空闲房屋名称
                                     2->...
                                     完成
                                      */
                                    while (true) {
                                        System.out.println("买房请输入房屋序号:");
                                        inputInteger = Integer.parseInt(Input());
                                        if (!(inputInteger >= 1 && inputInteger <= houses.size())) {
                                            //  System.out.println("已返回，请重新输入");
                                            break;
                                        }
                                        System.out.println("输入买房者ID（此处可以为0）");
                                        int inputInteger2 = Integer.parseInt(Input());
                                        /*查询ID， break;*/
                                        ResidentService residentService = new ResidentService();
                                        if (residentService.getResidentByResidentId(inputInteger2) == null) {
                                            System.out.println("ID无效");
                                            break;
                                        }
                                        houseService.housePurchase(inputInteger2, houses.get(inputInteger - 1), 100000 * houses.get(inputInteger - 1).getSize());
                                        /*添加买房记录，暂时不交（物业费  完成
                                        * */
                                        System.out.println("购房成功");
                                        break;
                                    }
                                }

                            }

                        }
//1.2
                        if (input.equals("2")) {
                            ResidentService residentService = new ResidentService();
                            List<Resident> residents = residentService.getAllResidents();
                            for (int i = 0; i < residents.size(); i++) {
                                System.out.println("ID：" + residents.get(i).getResident_id() + "  姓名：" + residents.get(i).getName() + "  电话：" + residents.get(i).getTel());
                            }
                            /*得到所有住户，展示其每月支出和代缴费用
                             List<>
                             ID+1->
                             ID+1->打印
                            * */
                            //1.2.1
                            while (true) {
                                System.out.println("输入用户ID，展示具体代缴款额");
                                int inputInteger = Integer.parseInt(Input());
                                if (!(inputInteger > 0 && inputInteger <= residents.size())) {
                                    break;
                                }
                                PaymentService paymentService = new PaymentService();
                                List<ResidentCost> residentCosts = paymentService.getResidentCostByResidentId(inputInteger);
                                for (int i = 0; i < residentCosts.size(); i++) {
                                    System.out.println("编号ID：" + residentCosts.get(i).getCost_id() + " 描述：" + residentCosts.get(i).getDescription() + " 时间：" + residentCosts.get(i).getTime() + " 金额:" + residentCosts.get(i).getCost() + " 支付情况:" + residentCosts.get(i).getState());
                                }
                                /*List<>
                                * 1->
                                * 2->
                                * */
                                System.out.println("输入代缴编号ID，完成缴费");
                                inputInteger = Integer.parseInt(Input());
                                if (inputInteger == 0) //inputInteger不在0-size之间
                                    break;
                                /*
                                * 缴费
                                * */
                                paymentService.pay(inputInteger);
                                System.out.println("缴费成功");
                            }
                        }
                    }
                }
                if (input.equals("2")) {
//2
                    HouseService houseService = new HouseService();
                    List<Community> communities = houseService.getCommunitys();
                    for (int i = 0; i < communities.size(); i++) {
                        System.out.println("小区编号：" + (i + 1) + "小区名称：" + communities.get(i).getName() + " ");
                    }
                    System.out.println("输入小区号");
                    int inputIN = Integer.parseInt(Input());
                    Community community = communities.get(inputIN - 1);
                    while (true) {
                        ParkingService parkingService = new ParkingService();
                        /**
                         * List<> 3个记录
                         临时车位信息
                         租用车位信息
                         购买车位信息
                         */
                        floor_3();
                        input = Input();
                        if (!(input.equals("1") || input.equals("2") || input.equals("3"))) {
                            System.out.println("已返回，请重新输入");
                            break;
                        }
                        if (input.equals("1")) {
//2.1
                            //   List<LeasedParkingSpace> leasedParkingSpacesEmpty = parkingService.getEmptyLeasedParkingSpaces(community);
                            while (true) {
                                List<TemporaryParkingSpace> temporaryParkingSpaces = parkingService.getAllTempPakingSpaces(community);
                                System.out.println("车位编号 " + "描述" + "状态");
                                for (int i = 0; i < temporaryParkingSpaces.size(); i++) {
                                    System.out.println((i + 1) + " " + temporaryParkingSpaces.get(i).getDescription() + "  " + temporaryParkingSpaces.get(i).getParking_state());
                                }

                              /*  System.out.println("1.临时车位借");
                                System.out.println("2.临时车位还");
                                input = Input();
                                if (!(input.equals("1") || input.equals("2"))) {
                                    System.out.println("已返回，请重新输入");
                                    break;
                                }*/
//2.1.1
                                System.out.println("选择状态为0的默认停车，为1的为还车");
                                int inputInteger = Integer.parseInt(Input());
                                /*判断*/
                                if (!(inputInteger > 0 && inputInteger <= temporaryParkingSpaces.size())) {
                                    break;
                                }//来进车
                                if (temporaryParkingSpaces.get(inputInteger - 1).getParking_state() == 0) {
                                    System.out.println("输入车牌号");
                                    input = Input();
                                    parkingService.tempParking(temporaryParkingSpaces.get(inputInteger - 1), input);
                                }//出车
                                if (temporaryParkingSpaces.get(inputInteger - 1).getParking_state() == 1) {
                                    System.out.println("输入花费");
                                    parkingService.tempLeaving(temporaryParkingSpaces.get(inputInteger - 1), (float) Integer.parseInt(Input()));
                                }
                                break;
                            }
                        }
                        if (input.equals("2")) {
//2.2
                         /*List<>
                         *1->
                         *2->
                         * 打印剩余车位
                         * */
                            while (true) {
                                List<LeasedParkingSpace> leasedParkingSpaces = parkingService.getAllLeasedParkingSpaces(community);
                                System.out.println("车位编号 " + "描述" + "状态");
                                for (int i = 0; i < leasedParkingSpaces.size(); i++) {
                                    System.out.println((i + 1) + " " + leasedParkingSpaces.get(i).getDescription() + "  " + leasedParkingSpaces.get(i).getParking_state());
                                }

                                System.out.println("租用车位请选择编号");
                                int inputInteger = Integer.parseInt(Input());
                                if (!(inputInteger > 0 && inputInteger <= leasedParkingSpaces.size())) {//编号不在0-size之间
                                    break;
                                }
                                System.out.println("输入租车位者ID（此处可以为0）");
                                int inputInteger2 = Integer.parseInt(Input());
                                        /*查询ID， break;*/
                                ResidentService residentService = new ResidentService();
                                if (residentService.getResidentByResidentId(inputInteger2) == null) {
                                    System.out.println("ID无效");
                                    break;
                                }
                                System.out.println("输入租车月数");
                                int inputInteger3 = Integer.parseInt(Input());
                                if (inputInteger3 <= 0) {//编号不在0-size之间
                                    break;
                                }
                                Date dateOne = new Date();
                                Date dateTwo = new Date();
                                dateTwo.setMonth(dateTwo.getMonth() + inputInteger3);
                                int x = parkingService.rentParkingSpace(residentService.getResidentByResidentId(inputInteger2), leasedParkingSpaces.get(inputInteger - 1), dateOne, dateTwo);
                                if (x == 1) {
                                    System.out.println("正有人使用，租不了");
                                }
                                /**插入
                                 * 租车位信息 插入
                                 * 租车起始时间，结束时间
                                 */
                                break;
                            }
                        }
                        if (input.equals("3")) {
                            //2.3
                         /*List<>
                         *1->
                         *2->
                         * 打印剩余车位
                         * */
                            while (true) {
                                List<OwnedParkingSpace> ownedParkingSpaces = parkingService.getEmptyOwnedParkingSpaces(community);
                                System.out.println("车位编号 " + "描述" + "空余车位如下");
                                for (int i = 0; i < ownedParkingSpaces.size(); i++) {
                                    System.out.println((i + 1) + " " + ownedParkingSpaces.get(i).getDescription() + "  ");
                                }
                                ;
                                System.out.println("购买车位请选择编号");
                                int inputInteger = Integer.parseInt(Input());
                                if (!(inputInteger > 0 && inputInteger <= ownedParkingSpaces.size())) {//编号不在0-size之间
                                    break;
                                }
                                System.out.println("输入买车位者ID（此处可以为0）");
                                int inputInteger2 = Integer.parseInt(Input());
                                        /*查询ID， break;*/
                                ResidentService residentService = new ResidentService();
                                if (residentService.getResidentByResidentId(inputInteger2) == null) {
                                    System.out.println("ID无效");
                                    break;
                                }
                                /**插入
                                 * 买车位信息 插入 （并记录车位管理费用
                                 *
                                 */
                                parkingService.purchaseParkingSpace(residentService.getResidentByResidentId(inputInteger2), ownedParkingSpaces.get(inputInteger - 1), 50000);
                                break;
                            }
                        }
                    }
                }
                if (input.equals("3")) {
//3
                    while (true) {
                        floor_4();
                        input = Input();
                        if (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4"))) {
                            System.out.println("已返回，请重新输入");
                            break;
                        }
                        if (input.equals("1")) {
//3.1
                        /*List<>搜有设备目前信息，好坏，（维修中待定）
                        * 1->
                        * 2->
                        * */
                            System.out.println("具体设备情况，输入编号");
                            int inputInteger = Integer.parseInt(Input());
                            if (true) {//编号不在0-size之间
                                break;
                            }
                            /**
                             *根据Id返回维修记录
                             * 根据ID返回检查记录
                             */
                        }
                        if (input.equals("2")) {
//3.2
                            //同上
                        }
                        if (input.equals("3")) {
//3.3
                            /**
                             * list<> 3个
                             *待分配
                             已分配未解决
                             已解决
                             */
                        }
                        if (input.equals("4")) {
//3.4
                            /*list<>
                            * 解决，未解决
                            * */
                        }


                    }
                }
                if (input.equals("4")) {
//4
                    /**
                     * List<> 4个记录
                     临时车费
                     用户缴纳
                     维修费用
                     其他费用
                     *打印目前总收支
                     *
                     */
                    while (true) {
                        PaymentService paymentService= new PaymentService();
                        floor_5();
                        input = Input();
                        if (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5"))) {
                            System.out.println("已返回，请重新输入");
                            break;
                        }
                        if (input.equals("1")) {
                            //打印临时车费缴纳记录，时间由最近到之前
                         List<TemporaryParkingRecord> temporaryParkingRecords =    paymentService.getTemParkingRecordCost();
                         System.out.println("停车位ID  "+"车牌号  "+"起始时间  "+"结束时间  "+"钱  ");
                         for(int i=0;i<temporaryParkingRecords.size();i++){
                             System.out.println(temporaryParkingRecords.get(i).getParking_space_id()+"   "+
                                     temporaryParkingRecords.get(i).getCar_id()+"   "+
                                     temporaryParkingRecords.get(i).getStart_time()+"   "+
                                     temporaryParkingRecords.get(i).getEnd_time()+"   "+
                                     temporaryParkingRecords.get(i).getCost()+"   ");
                         }
                        }
                        if (input.equals("2")) {
                            //打印用户缴纳记录，时间由最近到之前
                        }
                        if (input.equals("3")) {
                            //打印维修记录，时间由最近到之前
                        }
                        if (input.equals("4")) {
                            //打印其他记录，时间由最近到之前
                        }
                        if (input.equals("5")) {
                            //月初，更新物业收费和车位收费
                        }
                    }
                }
            }
        }
    }

    public static String Input() {
        System.out.println("请输入要选择的数字,返回上一层用 0 (不合法的输入作用和0相同)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public static void floor_1() {
        System.out.println("====================================");
        System.out.println("1.房屋信息管理");
        System.out.println("2.车位信息管理");
        System.out.println("3.设备信息管理");
        System.out.println("4.集团收支管理");
    }

    public static void floor_2() {
        System.out.println("====================================");
        System.out.println("1.闲置房查询");
        System.out.println("2.住户信息查询");
    }

    public static void floor_3() {
        System.out.println("====================================");
        System.out.println("1.临时车位信息");
        System.out.println("2.租用车位信息");
        System.out.println("3.购买车位信息");
    }

    public static void floor_4() {
        System.out.println("====================================");
        System.out.println("1.室外设备记录");
        System.out.println("2.室内设备记录");
        System.out.println("3.用户提出设备问题记录");
        System.out.println("4.投诉");
    }

    public static void floor_5() {
        System.out.println("====================================");
        System.out.println("1.临时车费缴纳");
        System.out.println("2.用户缴纳");
        System.out.println("3.维修费用");
        System.out.println("4.其他费用");
        System.out.println("5.每月收费一键更新");
    }
}

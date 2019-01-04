import model.entity.Resident;
import model.entity.house.Building;
import model.entity.house.Community;
import model.entity.house.House;
import service.HouseService;
import service.ResidentService;

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
                System.out.println("1.房屋信息管理");
                System.out.println("2.车位信息管理");
                System.out.println("3.设备信息管理");
                System.out.println("4.集团收支管理");
                String input = Input();
                if (input.equals("0")) {
                    System.out.println("已经最上层");
                    break;
                }
                if (input.equals("1")) {
                    //1
                    while (true) {
                        System.out.println("1.闲置房查询");
                        System.out.println("2.住户信息查询");

                        input = Input();
                        if (input.equals("0")) {
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
                             */
                            //1.1
                            while (true) {
                                System.out.println("请输入小区编号");
                                int inputInteger = Integer.parseInt(Input());
                                if (!(inputInteger >= 1 && inputInteger <= communities.size()))
                                    break;
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
                            */
                                while (true) {
                                    System.out.println("请输入楼编号");
                                    inputInteger = Integer.parseInt(Input());
                                    if (!(inputInteger >= 1 && inputInteger <= buildings.size()))
                                        break;
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
                                      */
                                    while (true) {
                                        System.out.println("买房请输入房屋序号:");
                                        inputInteger = Integer.parseInt(Input());
                                        if (!(inputInteger >= 1 && inputInteger <= houses.size()))
                                            break;
                                        System.out.println("输入买房者ID（此处可以为0）");
                                        int inputInteger2 = Integer.parseInt(Input());
                                        /*查询ID， break;*/
                                        ResidentService residentService = new ResidentService();
                                        if (residentService.getResidentByResidentId(inputInteger2) == null) {
                                            System.out.println("ID无效");
                                            break;
                                        }
                                        houseService.housePurchase(inputInteger2,houses.get(inputInteger-1),100000*houses.get(inputInteger-1).getSize());
                                        /*添加买房记录
                                        * */
                                        System.out.println("购房成功");
                                        break;
                                    }
                                }

                            }

                        }
                        //1.2
                        if (input.equals("2")) {
                            ResidentService residentService =new ResidentService();
                            List<Resident> residents = residentService.getAllResidents();
                            /*得到所有住户，展示其每月支出和代缴费用
                             List<>
                             ID+1->
                             ID+1->打印
                            * */
                            //1.2.1
                            while (true) {
                                System.out.println("输入用户ID（此处可以为0），展示具体代缴款额");
                                int inputInteger = Integer.parseInt(Input());

                                /*List<>
                                * 1->
                                * 2->
                                * */
                                System.out.println("输入代缴编号，完成缴费");
                                inputInteger = Integer.parseInt(Input());
                                if (inputInteger==0)
                                    break;
                                /*
                                * 缴费
                                * */
                            }
                        }
                    }
                }


                if (input.equals("2")) {
                }
                if (input.equals("3")) {
                }
                if (input.equals("4")) {
                }
            }


        }


    }

    public static String Input() {
        System.out.println("请输入要选择的数字(不合法的输入作用和0相同)，返回上一层用0");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
}

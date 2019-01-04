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
                                if (inputInteger == 0)
                                    break;
                                /*coding
                              List<>
                              打印
                              1->楼宇名称
                              2->...
                            */
                                while (true) {
                                    System.out.println("请输入楼编号");
                                    inputInteger = Integer.parseInt(Input());
                                    if (inputInteger == 0)
                                        break;
                                     /*coding
                                     List<>
                                    打印
                                    1->空闲房屋名称
                                     2->...
                                      */
                                    while (true) {
                                        System.out.println("买房请输入房屋序号:");
                                        inputInteger = Integer.parseInt(Input());
                                        if (inputInteger == 0)
                                            break;
                                        System.out.println("输入买房者ID");
                                        inputInteger = Integer.parseInt(Input());
                                        if (inputInteger == 0)
                                            break;
                                        /*查询ID， break;*/
                                        System.out.println("ID无效");
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
                            /*得到所有住户
                             List<>
                             ID+1->
                             ID+1->打印
                            * */
     //1.2.1
                            while (true){
                                System.out.println("输入用户ID，展示其每月支出和代缴费用");
                                int inputInteger = Integer.parseInt(Input());
                                if (inputInteger==0)
                                    break;
                                /*
                                * 打印支出信息
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
        System.out.println("请输入要选择的数字，返回上一层用0");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
}

package com.p;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Author:Fanleilei
 * Created:2019/3/29 0029
 */
public class CheckOut {

    public  static  Scanner scanner=new Scanner(System.in);

    public static  AtomicInteger atomicInteger=new AtomicInteger();

    //存储商品的map
    public static  Map<Integer,Merchandise> map=new HashMap<>();
    public static Merchandise goods;
    public static Order order;
    //存储订单的map
    public static Map<Integer,Order> Ordermap=new HashMap<>();



    public static void start(){

        System.out.println("***************欢迎使用收银台***************");
        System.out.println("    [U] 使用 [S] 设置 [A]关于 [Q]退出       ");
        System.out.println("           输入U S A Q进入操作              ");
        System.out.println("*******************************************");

        while(true) {
            String choice = scanner.nextLine();
            switch (choice) {

                case "U":
                    Using();
                    break;
                case "S":
                    Setting();
                    break;

                case "A":
                    About();
                    break;

                case "Q":
                    Quit();
                    break;

                default:
                    System.out.println("输入不正确，请重新输入：");
                    break;

            }
        }

        }
    public static void Using(){
        System.out.println("*****************买单功能******************");
        System.out.println("  [S] 查看 [A] 下单 [D]取消 [L]浏览  [R]返回");
        System.out.println("           输入S A D L R进入操作            ");
        System.out.println("*******************************************");
        System.out.println("*****************商品清单*******************");
        System.out.println("         编号   产品名称     单价       ");
        //打印商品清单
        Printgoods();

        while(true){
            String choice=scanner.nextLine();
            switch(choice) {

                case "S":
                    check();
                    break;
                case "A":
                    buy();
                    break;

                case "D":
                    cancel();
                    break;
                case "L":
                    glance();
                    break;
                case "R":
                    start();
                    break;

                default:
                    System.out.println("请输入正确指令");
                    break;


            }
        }

    }
    //查看订单
    public static  void check(){

        for(Map.Entry<Integer,Order> entry:Ordermap.entrySet()){
            order=entry.getValue();
            PrintOrder(order);

        }

    }

    //下单
    public static void buy(){


            System.out.println("请输入下单信息[编号 数量]（如下格式：1 2）");

            String content = scanner.nextLine();
            //下单商品的单号
            Integer num = Integer.parseInt(content.split(" ")[0]);
            //商品的数量
            int count = Integer.parseInt(content.split(" ")[1]);

            //订单编号
        int ordernum=atomicInteger.addAndGet(1);

            order = new Order(ordernum, num,
                    map.get(num).getName(), count,
                    map.get(num).getPrice(),
                    map.get(num).getPrice() * count);
            //将订单加入到Ordermap中
            Ordermap.put(ordernum, order);
            //打印该订单
            PrintOrder(order);


    }

    //取消
    public static void cancel(){
        System.out.println("请输入取消信息[编号 数量]（如下格式：1 2）");

        String content=scanner.nextLine();
        //获取要取消的商品的编号
        int Ordernum=Integer.parseInt(content.split(" ")[0]);
        //要取消的数量
        int deletenum=Integer.parseInt(content.split(" ")[1]);
        //原本下单时该商品的数量
        int OrderNUM=Ordermap.get(Ordernum).getCount();
        //剩下的数量
        int leaveNUM=OrderNUM-deletenum;

        //如果取消之后，订单商品的数量为0，则删除该订单;
        //如果不为0，则重新设置count和totalprice
        for(Map.Entry<Integer,Order> entry:Ordermap.entrySet()){

            if(entry.getValue().getNum()==Ordernum){
                if(leaveNUM==0){

                    Ordermap.remove(entry);
                }else {
                    //重新设置数量count
                    entry.getValue().setCount(leaveNUM);
                    //重新设置总价totalprice
                    entry.getValue().setTotalprice(leaveNUM*entry.getValue().getPrice());
                    //打印该订单
                    PrintOrder(entry.getValue());
                    break;
                }

            }

        }
/*
            //获取Ordermap中所有的Order
        Collection<Order> AllOrders= Ordermap.values();
        for( Order order:AllOrders){
            if(order.getNum()==Ordernum){

                    order.setCount(leaveNUM);
                    order.setPrice(leaveNUM*order.getPrice());
                    PrintOrder(order);
            }
        }*/
    }



    //浏览
    public static void glance(){

        Printgoods();
    }



    public static void Setting(){

        System.out.println("******************设置功能******************");
        System.out.println("  [S] 查看 [A] 上架 [D] 下架 [U]修改  [R]返回 ");
        System.out.println("              输入S A D U R进入操作         ");
        System.out.println("*******************************************");


        while(true){
            String choice=scanner.nextLine();
            switch(choice) {

                case "S":
                    SeeGoods();
                    break;
                case "A":
                    Shangjia();
                    break;
                case "D":
                    Xiajia();
                    break;
                case "U":
                    change();
                    break;
                case "R":
                    start();
                    break;
                default:
                    System.out.println("请输入正确指令");
                    break;

            }
        }

    }

    //查看物品
    public static  void SeeGoods(){

        Printgoods();
    }
    //添加商品
    public  static void AddGoods(Integer num,Merchandise goods){

        map.put(num,goods);
    }
    //判断货架是否满了
    public static boolean Isfull(){

        if(map.size()==10){

            return true;
        }
        return  false;
    }
    //上架
    public static void Shangjia(){
        System.out.println("请输入上架商品信息（如下格式：1 餐巾纸 2.1）");

        String content=scanner.nextLine();
        Integer num=Integer.parseInt(content.split(" ")[0]);
       String name=content.split(" ")[1];
       double price=Double.parseDouble(content.split(" ")[2]);

       //如果输入的编号已经被占用，则提示错误
        /*for(Map.Entry<Integer,Merchandise> entry:map.entrySet()) {
            if (  entry.getKey()==num) {

                System.out.println("该编号已被占用");

            } else {*/

               goods = new Merchandise(name, price);
                if (Isfull()) {
                    System.out.println("货架已满，该商品上不了架");

                } else {
                    //添加商品
                    AddGoods(num, goods);

                }
         /*  }
        }*/

       //将上架的商品存储在mapp集合中
        /*Map<Integer,String> map=new HashMap<>();
        Map<Map<Integer,String>,Double> mapp=new HashMap<>();
        map.put(num,name);
        mapp.put(map,price);

        //将商品清单打印出来
        System.out.println("*****************商品清单*******************");
        System.out.println("         编号   产品名称     单价       ");
        for(Map.Entry<Map<Integer,String>,Double>  p:mapp.entrySet()){
            Map<Integer,String> t=p.getKey();

            for(Map.Entry<Integer,String> entry:t.entrySet()){
                System.out.println("          "+entry.getKey()+"   "+entry.getValue()+"    "+p.getValue());

            }

        }
        for(int i=mapp.size();i<=10;i++){
            System.out.println("          "+i+"   --[未上架]  "+"  0.0");

        }*/


        //打印商品
        Printgoods();



    }
    //下架
    public static void Xiajia(){
        System.out.println("请输入下架商品信息（如下格式：1 餐巾纸  2.1）");

        String content=scanner.nextLine();
        Integer num=Integer.parseInt(content.split(" ")[0]);

        if(num>=1&&num<=10){
            //将商品下架
            map.remove(num);
        }else{

            System.out.println("请输入正确的下架编码号");
        }

        //打印商品
        Printgoods();


    }
    //修改
    public  static  void change(){
        System.out.println("请输入修改商品信息（如下格式：1 餐巾纸  2.1）");

        String content=scanner.nextLine();
        int num=Integer.parseInt(content.split(" ")[0]);
        goods=map.get(num);

        goods.setName(content.split(" ")[1]);
        goods.setPrice(Double.parseDouble(content.split(" ")[2]));

       //打印
       Printgoods();
    }
    public static void About(){

        System.out.println("********************************************");
        System.out.println("         名称：简易收银台                    ");
        System.out.println("         功能：字符界面的收银台系统           ");
        System.out.println("         作者：范蕾蕾                        ");
        System.out.println("*********************************************");
    }
    public static void Quit(){

        System.out.println("**********************************************");
        System.out.println("              欢迎使用，下次再见               ");
        System.out.println("**********************************************");
        System.exit(0);
    }

    //打印商品
    public static void Printgoods(){
       System.out.println("*****************商品清单*******************");
        System.out.println("         编号   产品名称     单价       ");

        for(int i=1;i<=10;i++){
           int count=0;
            for (Map.Entry<Integer, Merchandise> entry : map.entrySet()) {
                if(i==entry.getKey()){
                    System.out.println("          " + entry.getKey() + "    " + entry.getValue().getName() + "        " + entry.getValue().getPrice());
                    break;
                }
                count++;
            }
            if(count==map.size()) {
                System.out.println("          " + i + "    --[未上架]  " + "     0.0");
            }

         }

        System.out.println("*******************************************");

    }

    //打印订单
    public static  void PrintOrder(Order order){

        System.out.println("====================================");
        System.out.println("编号："+order.getOrdernum());//订单号
        System.out.println("打印时间："+new Date().toString());
        System.out.println("====================================");
        System.out.println("编号      "+"名称     "+"数量    "+"单价");
        System.out.println(order.getNum()+"     "+order.getName()+"   "+order.getCount()+"   "+order.getPrice());
        System.out.println("====================================");
        System.out.println("总价："+order.getTotalprice());
        System.out.println("====================================");



    }
    public static void main(String[] args) {
        start();

    }
}

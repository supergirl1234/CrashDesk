package com.p;

/**
 * Author:Fanleilei
 * Created:2019/4/3 0003
 */
public class Order {

    public int ordernum;
    public int num;
    public String name;
    public int count;
    public double price;
    public double totalprice;

    public Order( int ordernum,int num, String name, int count, double price, double totalprice) {
        this.ordernum = ordernum;
        this.num = num;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalprice = totalprice;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }


}

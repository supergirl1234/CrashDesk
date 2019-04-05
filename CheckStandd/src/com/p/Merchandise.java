package com.p;

/**
 * Author:Fanleilei
 * Created:2019/3/29 0029
 */
public class Merchandise {

    private Integer num;
    private String name;
    private double price;

    public Merchandise(int num, String name, double price) {
        this.num = num;
        this.name = name;
        this.price = price;
    }

    public Merchandise(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

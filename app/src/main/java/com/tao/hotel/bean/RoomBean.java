package com.tao.hotel.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * @author: Tao
 * @time: 2017/12/17
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public class RoomBean extends DataSupport {

    @Column(nullable = false)
    private int hotelId ;

    @Column(nullable = false)
    private String name ;

    @Column(defaultValue = "大床")
    private String bedDir;

    @Column(defaultValue = "30")
    private int plane;

    private double price;

    private int satisfaction;

    private int comment;

    private int number;

    private int image;

    public RoomBean() {
    }

    public RoomBean(int hotelId, String name, String bedDir, int plane, double price, int satisfaction,
                    int comment, int number, int image) {
        this.hotelId = hotelId;
        this.name = name;
        this.bedDir = bedDir;
        this.plane = plane;
        this.price = price;
        this.satisfaction = satisfaction;
        this.comment = comment;
        this.number = number;
        this.image = image ;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBedDir() {
        return bedDir;
    }

    public void setBedDir(String bedDir) {
        this.bedDir = bedDir;
    }

    public int getPlane() {
        return plane;
    }

    public void setPlane(int plane) {
        this.plane = plane;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "RoomBean{" +
                "hotelId=" + hotelId +
                ", name='" + name + '\'' +
                ", bedDir='" + bedDir + '\'' +
                ", plane=" + plane +
                ", price=" + price +
                ", satisfaction=" + satisfaction +
                ", comment=" + comment +
                ", number=" + number +
                ", image=" + image +
                '}';
    }
}

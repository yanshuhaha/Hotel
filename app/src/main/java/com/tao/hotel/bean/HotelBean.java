package com.tao.hotel.bean;

import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @author: Tao
 * @time: 2017/12/17
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public class HotelBean extends DataSupport {

    @Column(unique = true)
    private int hotelId ;

    private double longitude ;

    private double latitude ;

    private String name ;

    private String locationDir ;

    private int level ;

    private double score ;

    private double price ;

    @Column(defaultValue = "店主很懒什么都没有留下")
    private String describe ;

    private List<RoomBean> roomBeanList ;

    private String location;


    public HotelBean() {
    }

    public HotelBean(int hotelId, double longitude, double latitude, String name,
                     String locationDir, int level, double score, double price, String describe) {
        this.hotelId = hotelId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
        this.locationDir = locationDir;
        this.level = level;
        this.score = score;
        this.price = price;
        this.describe = describe;
        this.roomBeanList = roomBeanList;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int id) {
        this.hotelId = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationDir() {
        return locationDir;
    }

    public void setLocationDir(String locationDir) {
        this.locationDir = locationDir;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<RoomBean> getRoomBeanList() {
        return roomBeanList;
    }

    public void setRoomBeanList(List<RoomBean> roomBeanList) {
        this.roomBeanList = roomBeanList;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "HotelBean{" +
                "hotelId=" + hotelId +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", name='" + name + '\'' +
                ", locationDir='" + locationDir + '\'' +
                ", level=" + level +
                ", score=" + score +
                ", price=" + price +
                ", describe='" + describe + '\'' +
                ", roomBeanList=" + roomBeanList +
                ", location='" + location + '\'' +
                '}';
    }
}

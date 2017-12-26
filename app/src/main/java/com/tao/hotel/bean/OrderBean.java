package com.tao.hotel.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * @author: Tao
 * @time: 2017/12/25
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public class OrderBean extends DataSupport {


    /**
     * 入住人
     */
    private String personName ;

    /**
     * 用户电话
     */
    private String personPhone ;

    /**
     * 酒店id
     */
    private int hotelId ;

    /**
     * 酒店名
     */
    private String hotelName;

    /**
     * 酒店电话
     */
    private long hotelPhone ;

    /**
     * 地址json
     */
    private String location;

    /**
     * 预付房款
     */
    private double price ;


    /**
     * 订单状态 取值 0 1 2 3 4
     * 0 订单初始值
     * 1 付款完毕
     * 2 酒店确认
     * 3 客户入住
     * 4 交易完成
     */
    @Column(defaultValue = "1")
    private int state = 1;

    /**
     * 房间数
     */
    private int roomNum ;

    /**
     * 入住时间 Data json
     */
    private String startTime;

    /**
     * 离店时间 Data json
     */
    private String endTime;

    /**
     * Room json
     */
    private String rooms;

    public OrderBean() {
    }

    public long getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(long hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "personName='" + personName + '\'' +
                ", personPhone='" + personPhone + '\'' +
                ", hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", hotelPhone=" + hotelPhone +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", state=" + state +
                ", roomNum=" + roomNum +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", rooms='" + rooms + '\'' +
                '}';
    }
}

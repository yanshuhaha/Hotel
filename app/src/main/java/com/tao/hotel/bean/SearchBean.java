package com.tao.hotel.bean;

import com.baidu.mapapi.model.LatLng;

import java.util.Date;

/**
 * @author: Tao
 * @time: 2017/12/13
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 搜索的封装类
 */
public class SearchBean {

    private LatLng latLng ;

    private Date startDate ;

    private Date endDate ;

    private String keyWord ;

    private int level ;

    public boolean haveNull(){
        if (latLng == null){
            return true;
        }

        if (startDate == null){
            return true;
        }

        if (endDate == null ){
            return true;
        }

        if (keyWord == null && keyWord.isEmpty()){
            return true;
        }
        return false;
    }


    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "SearchBean{" +
                "latLng=" + latLng +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", keyWord='" + keyWord + '\'' +
                ", level=" + level +
                '}';
    }
}

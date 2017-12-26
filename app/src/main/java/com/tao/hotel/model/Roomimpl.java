package com.tao.hotel.model;

import com.tao.hotel.bean.RoomBean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @author: Tao
 * @time: 2017/12/20
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public class Roomimpl implements InterfaceRoom {

    @Override
    public boolean sava(RoomBean bean){
        return bean.save();
    }

    @Override
    public void savaList(List<RoomBean> list){
        for (int i = 0 ; i < list.size(); i ++){
            sava(list.get(i));
        }
    }

    @Override
    public List<RoomBean> queryAll(){
        return DataSupport.findAll(RoomBean.class);
    }

    @Override
    public List<RoomBean> queryByHotelId(String hotelId){
        return DataSupport.select().where(" hotelid = ? ",hotelId).find(RoomBean.class);
    }

    @Override
    public void deleteAll(){
        DataSupport.deleteAll(RoomBean.class);
    }
}

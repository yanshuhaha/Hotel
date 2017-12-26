package com.tao.hotel.model;

import com.tao.hotel.bean.HotelBean;

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
public class Hotelimpl implements InterfaceHotel {

    @Override
    public boolean saveHotel(HotelBean bean){
        return bean.save();
    }

    @Override
    public int updateHotel(HotelBean bean,long id){
        return bean.update(id);
    }

    @Override
    public int  deleteHotel(long id){
        return DataSupport.delete(HotelBean.class,id);
    }

    @Override
    public HotelBean queryHotel(long id){
        return DataSupport.find(HotelBean.class,id);
    }

    public HotelBean queryByHotelId(String hotelId){
       List<HotelBean> ls = DataSupport.select().where("hotelid = ?" ,hotelId).find(HotelBean.class);
       return ls.get(0);
    }

    @Override
    public List<HotelBean> queryAll(){
        return DataSupport.findAll(HotelBean.class);
    }
}

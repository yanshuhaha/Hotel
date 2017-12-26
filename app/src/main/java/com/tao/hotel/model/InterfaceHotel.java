package com.tao.hotel.model;

import com.tao.hotel.bean.HotelBean;

import java.util.List;

/**
 * @author: Tao
 * @time: 2017/12/17
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public interface InterfaceHotel {

    public boolean saveHotel(HotelBean bean);

    public int updateHotel(HotelBean bean,long id);

    public int  deleteHotel(long id);

    public HotelBean queryHotel(long id);

    public List<HotelBean> queryAll();

}

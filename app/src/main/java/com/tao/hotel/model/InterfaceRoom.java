package com.tao.hotel.model;

import com.tao.hotel.bean.RoomBean;

import java.util.List;

/**
 * @author: Tao
 * @time: 2017/12/20
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public interface InterfaceRoom {

    /**
     * 保存
     * @param bean
     * @return
     */
    public boolean sava(RoomBean bean);

    /**
     * 保存一堆
     * @param list
     */
    public void savaList(List<RoomBean> list);

    /**
     * 查询所有
     * @return
     */
    public List<RoomBean> queryAll();

    /**
     * 通过hotelId查询
     * @param hotelId
     * @return
     */
    public List<RoomBean> queryByHotelId(String hotelId);

    /**
     * 删除一堆
     */
    public void deleteAll();
}

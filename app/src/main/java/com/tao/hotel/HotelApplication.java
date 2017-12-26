package com.tao.hotel;

import android.app.Application;
import com.baidu.mapapi.SDKInitializer;

import org.litepal.LitePal;

/**
 * @author: Tao
 * @time: 2017/12/13
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public class HotelApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        initBaiduMap();
    }

    private void initBaiduMap() {
        SDKInitializer.initialize(this);
    }

}

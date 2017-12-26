package com.tao.hotel.maputlis;

import com.baidu.location.BDLocation;

/**
 * @author: Tao
 * @time: 2017/12/13
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 定位回调接口
 */
public interface LocationCallBack {

    void success(BDLocation location);

    void fail(String string);
}

package com.tao.hotel.utlis;

import android.util.Log;

/**
 * @author: Tao
 * @time: 2017/12/8
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public class L {

    private static boolean isLog = true ;

    public static void i(String tag, String msg){
        if (isLog)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg){
        if (isLog)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg){
        if (isLog)
            Log.e(tag, msg);
    }

    public static void w(String tag, String msg){
        if (isLog)
            Log.w(tag, msg);
    }

    public static void v(String tag, String msg){
        if (isLog)
            Log.v(tag, msg);
    }

}

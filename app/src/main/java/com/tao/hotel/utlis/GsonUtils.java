package com.tao.hotel.utlis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class GsonUtils {

    private static Gson mGson = new Gson();

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            return mGson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T>List<T> jsonToList(String json, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try{
            list = mGson.fromJson(json,new TypeToken<List<T>>(){}.getType());
        }catch (Exception e){

        }
        return list;
    }

    public static String toJson(Object src) {
        return mGson.toJson(src);
    }

    /**
     * 修改
     */
    public static List<T> jsonToLists(String json, Class<T> calzz){
        return mGson.fromJson(json, new TypeToken<List<T>>(){}.getType());
    }

}

package com.tao.hotel.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.tao.hotel.R;
import com.tao.hotel.bean.HotelBean;
import com.tao.hotel.bean.RoomBean;
import com.tao.hotel.model.Hotelimpl;
import com.tao.hotel.model.Roomimpl;
import com.tao.hotel.utlis.GsonUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DateActivity extends AppCompatActivity {

    private List<HotelBean> beans;

    @BindView(R.id.bt_date_add)
    Button btDateAdd;
    @BindView(R.id.bt_room_add)
    Button btRoomAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        ButterKnife.bind(this);
    }

    private void addDate() {
        DataSupport.deleteAll(HotelBean.class);
        beans = new ArrayList<>();

        beans.add(new HotelBean(1, 113.038539, 23.158981, "紫荆公寓",
                "广东东软学院附近", 1, 4.1, 188, "好评如潮，年轻人最爱"));
        beans.add(new HotelBean(2, 113.025675, 23.138155, "维也纳",
                "广东东软学院附近", 2, 4.2, 288, "性价比比较高，毕竟价格很美丽！"));
        beans.add(new HotelBean(3, 113.0274, 23.130611, "锦江之星",
                "广东东软学院附近", 3, 4.3, 388, "超值享受，静谧又开阔，休息的很好！"));
        beans.add(new HotelBean(4, 113.033135, 23.124126, "如家",
                "广东东软学院附近", 4, 4.5, 588, "房间很干净、舒适。卫生间特别棒，可以边看电视边洗澡。" +
                "价格也很划算。出行很方便，吃喝玩乐应有尽有。健身房、游泳池都有，楼下有咖啡馆，二楼酒吧、西餐厅、面馆。"));
        beans.add(new HotelBean(5, 113.134671, 23.115467, "汉庭酒店",
                "广东东软学院附近", 5, 4.7, 1188, "酒店地理位置优越，周边歺饮方便，但性价比一般，其他还不错的"));
        beans.add(new HotelBean(6, 113.146744, 23.109289, "莱茵酒店",
                "广东东软学院附近", 4, 4.8, 688, "环境好，交通便利，服务好，很棒的入住，下次还会再来"));
        beans.add(new HotelBean(7, 113.218379, 23.101644, "广州国际酒店",
                "广东东软学院附近", 5, 4.9, 1688, "酒店的床上用品真的很软，能睡个好好觉"));
        beans.add(new HotelBean(8, 113.339199, 23.122712, "长隆童话公寓",
                "广东东软学院附近", 3, 4.9, 488, "服务热情，施实也很新。"));

        for (HotelBean bean : beans) {

            GeoCoder mSearch = GeoCoder.newInstance();
            OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {

                @Override
                public void onGetGeoCodeResult(GeoCodeResult result) {

                    if (result != null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                        //没有检索到结果
                    }

                    //获取地理编码结果
                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                    if (result != null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                        //没有找到检索结果
                        bean.setLocation(GsonUtils.toJson(result));
                        bean.setLocationDir(result.getSematicDescription());
                        bean.save();
                    }
                }
            };
            mSearch.setOnGetGeoCodeResultListener(listener);
            mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                    .location(new LatLng(bean.getLatitude(), bean.getLongitude())));
            mSearch.destroy();
        }


    }

    private void addRoom() {
        new Roomimpl().deleteAll();
        List<HotelBean> beans = new Hotelimpl().queryAll();
        for (HotelBean bean:beans) {
            /*
            public RoomBean(int hotelId, String name, String bedDir, int plane, int price, int satisfaction, int comment, int number)
            */

            List<RoomBean> roomBeans = new ArrayList<>();
            roomBeans.add(new RoomBean(bean.getHotelId(),"豪华单人房","大床",30,
                    bean.getPrice()+40,95,5,5,R.drawable.room3));
            roomBeans.add(new RoomBean(bean.getHotelId(),"普通单人房","大床",30,
                     bean.getPrice(),96,5,5,R.drawable.room1));
            roomBeans.add(new RoomBean(bean.getHotelId(),"豪华双人房","大床",45,
                    bean.getPrice()+100,97,5,5,R.drawable.room4));
            roomBeans.add(new RoomBean(bean.getHotelId(),"普通双人房","大床",45,
                    bean.getPrice()+80,95,5,5,R.drawable.room2));
            roomBeans.add(new RoomBean(bean.getHotelId(),"情侣双人房","大床",45,
                    bean.getPrice()+120,99,5,5,R.drawable.room5));
            new Roomimpl().savaList(roomBeans);
        }
    }

    @OnClick({R.id.bt_date_add, R.id.bt_room_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_date_add:
                addDate();
                break;
            case R.id.bt_room_add:
                addRoom();
                break;
        }
    }

}

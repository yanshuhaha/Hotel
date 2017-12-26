package com.tao.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.tao.hotel.R;
import com.tao.hotel.bean.HotelBean;
import com.tao.hotel.maputlis.DefaultLocationClientOption;
import com.tao.hotel.maputlis.LocationCallBack;
import com.tao.hotel.maputlis.MyLocationListener;
import com.tao.hotel.model.Hotelimpl;
import com.tao.hotel.utlis.GsonUtils;
import com.tao.hotel.utlis.L;
import com.tao.hotel.utlis.T;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HotelMapActivity extends BaseActivity {

    private static final String TAG = "HotelMapActivity";

    private InfoWindow mInfoWindow;
    private List<Marker> list;
    private List<HotelBean> hotelBeans;

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = null;
    private BaiduMap baiduMap ;

    @BindView(R.id.hotel_map_Texturemap)
    TextureMapView hotelMapTexturemap;
    @BindView(R.id.iv_hotel_map_back)
    ImageView ivHotelMapBack;
    @BindView(R.id.ed_hotelmap_search)
    EditText edHotelmapSearch;
    @BindView(R.id.bt_choose_all_hotel_map)
    Button btChooseAllHotelMap;
    @BindView(R.id.bt_choose_cheap_hotel_map)
    Button btChooseCheapHotelMap;
    @BindView(R.id.bt_choose_expensive_hotel_map)
    Button btChooseExpensiveHotelMap;
    @BindView(R.id.iv_item)
    ImageView ivItem;
    @BindView(R.id.tv_item_name)
    TextView tvItemName;
    @BindView(R.id.tv_item_price)
    TextView tvItemPrice;
    @BindView(R.id.tv_item_score)
    TextView tvItemScore;
    @BindView(R.id.tv_item_location)
    TextView tvItemLocation;
    @BindView(R.id.tv_item_content)
    TextView tvItemContent;
    @BindView(R.id.linear_item_hotel)
    LinearLayout linearItemHotel;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_hotel_map;
    }

    @Override
    protected void init() {
        super.init();
        hotelBeans = new Hotelimpl().queryAll();
        L.i(TAG,hotelBeans.size()+"");
        baiduMap = hotelMapTexturemap.getMap();
        float f = baiduMap.getMaxZoomLevel();
        MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(f - 5);
        //大小按需求计算就可以
        baiduMap.animateMapStatus(u);
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);

        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.location_fill);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
        baiduMap.setMyLocationConfiguration(config);
        list = new ArrayList<>();

        //定位
        baiduMapWork();
        //显示标记
        for (int i =0 ; i < hotelBeans.size(); i ++ ) {
            HotelBean bean = hotelBeans.get(i);
            ReverseGeoCodeResult reverseGeoCodeResult = GsonUtils.jsonToBean(hotelBeans.get(i).getLocation(), ReverseGeoCodeResult.class);
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.hotel_red);
            MarkerOptions ooA = new MarkerOptions().position(reverseGeoCodeResult.getLocation()).icon(bitmap)
                    .zIndex(9).draggable(true).title(i+"");
            list.add((Marker) (baiduMap.addOverlay(ooA)));
        }

        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                hideItem();
                View view = getLayoutInflater().inflate(R.layout.item_simple_hotel,null);
                TextView tvName = view.findViewById(R.id.tv_name_simple_item);
                TextView tvScore = view.findViewById(R.id.tv_score_simple_item);
                TextView tvPrince = view.findViewById(R.id.tv_price_simple_item);

                HotelBean bean = hotelBeans.get(Integer.parseInt(marker.getTitle()));
                ReverseGeoCodeResult reverseGeoCodeResult = GsonUtils.jsonToBean(bean.getLocation(),ReverseGeoCodeResult.class);

                tvName.setText(bean.getName());
                tvPrince.setText("¥"+bean.getPrice());
                tvScore.setText(bean.getScore()+ "分");
                view.setOnClickListener(view1 -> showItem(bean));
                mInfoWindow = new InfoWindow(view, reverseGeoCodeResult.getLocation(), -47);
                baiduMap.showInfoWindow(mInfoWindow);
                return true;
            }
        });
    }

    private void showItem(HotelBean bean) {
        linearItemHotel.setVisibility(View.VISIBLE);
        tvItemName.setText(bean.getName());
        tvItemPrice.setText(bean.getPrice() + "");
        tvItemScore.setText(bean.getScore() + "");
        tvItemLocation.setText(bean.getLocationDir());
        tvItemContent.setOnClickListener(view -> goRoom(bean.getHotelId()));
    }

    private void goRoom(int hotelId) {
        Intent intent = new Intent(HotelMapActivity.this,RoomActivity.class);
        intent.putExtra("hotelId",hotelId);
        startActivity(intent);
    }

    private void hideItem(){
        linearItemHotel.setVisibility(View.GONE);
    }


    @OnClick({R.id.iv_hotel_map_back, R.id.ed_hotelmap_search, R.id.bt_choose_all_hotel_map,
            R.id.bt_choose_cheap_hotel_map, R.id.bt_choose_expensive_hotel_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_hotel_map_back:
                break;
            case R.id.ed_hotelmap_search:
                break;
            case R.id.bt_choose_all_hotel_map:
                break;
            case R.id.bt_choose_cheap_hotel_map:
                break;
            case R.id.bt_choose_expensive_hotel_map:
                break;
        }
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar.with(this)
                .statusBarColor(R.color.background)
                .init();
    }

    private void baiduMapWork(){
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        myListener = new MyLocationListener(mLocationClient, new LocationCallBack() {
            @Override
            public void success(BDLocation location) {
                // 构造定位数据
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100)
                        .latitude(location.getLatitude())
                        .longitude(location.getLongitude())
                        .build();

                // 设置定位数据
                baiduMap.setMyLocationData(locData);

            }

            @Override
            public void fail(String string) {
                T.show(HotelMapActivity.this, "定位失败，请检查网络或者GPS", Toast.LENGTH_SHORT);
            }
        });
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.setLocOption(DefaultLocationClientOption.get());
        mLocationClient.start();

    }
}

package com.tao.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.tao.hotel.R;
import com.tao.hotel.maputlis.DefaultLocationClientOption;
import com.tao.hotel.maputlis.LocationCallBack;
import com.tao.hotel.maputlis.MyLocationListener;
import com.tao.hotel.utlis.GsonUtils;
import com.tao.hotel.utlis.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends BaseActivity {

    public static final int SUCCESS = 0x1;

    @BindView(R.id.mTexturemap)
    TextureMapView mTexturemap;
    @BindView(R.id.map_ok)
    Button mapOk;

    private BaiduMap mBaiduMap;
    private LatLng latLng;

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = null;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_map;
    }

    @Override
    protected void init() {
        super.init();
        mBaiduMap = mTexturemap.getMap();
        float f = mBaiduMap.getMaxZoomLevel();
        MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(f - 5);//大小按需求计算就可以
        mBaiduMap.animateMapStatus(u);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.location_fill);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfiguration(config);
        initBaiduMap();


        // 当不需要定位图层时关闭定位图层
        //mBaiduMap.setMyLocationEnabled(false);


        BaiduMap.OnMapClickListener listener = new BaiduMap.OnMapClickListener() {
            /**
             * 地图单击事件回调函数
             * @param point 点击的地理坐标
             */
            public void onMapClick(LatLng point) {
                //T.show(MapActivity.this,point.toString(),Toast.LENGTH_LONG);
                latLng = point;
                mBaiduMap.clear();

                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.location_fill);

                //构建MarkerOption，用于在地图上添加Marker

                OverlayOptions option = new MarkerOptions()
                        .position(point)
                        .icon(bitmap);
                mBaiduMap.addOverlay(option);
                mapOk.setVisibility(View.VISIBLE);
            }

            /**
             * 地图内 Poi 单击事件回调函数
             * @param poi 点击的 poi 信息
             */
            public boolean onMapPoiClick(MapPoi poi) {
                //T.show(MapActivity.this,poi.toString(),Toast.LENGTH_LONG);
                latLng = poi.getPosition();
                mBaiduMap.clear();

                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.location_fill);

                //构建MarkerOption，用于在地图上添加Marker

                OverlayOptions option = new MarkerOptions()
                        .position(latLng)
                        .icon(bitmap);
                mBaiduMap.addOverlay(option);
                mapOk.setVisibility(View.VISIBLE);
                return true;
            }
        };
        mBaiduMap.setOnMapClickListener(listener);
    }

    private void initBaiduMap() {
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
                mBaiduMap.setMyLocationData(locData);

            }

            @Override
            public void fail(String string) {
                T.show(MapActivity.this, "定位失败，请检查网络或者GPS", Toast.LENGTH_SHORT);
            }
        });
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.setLocOption(DefaultLocationClientOption.get());
        mLocationClient.start();

    }

    @Override
    protected void onDestroy() {
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mTexturemap.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mTexturemap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mTexturemap.onPause();
    }

    @OnClick(R.id.map_ok)
    public void onViewClicked() {
        mapOk.setEnabled(false);
        GeoCoder mSearch = GeoCoder.newInstance();
        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {

            public void onGetGeoCodeResult(GeoCodeResult result) {

                if (result!=null||result.error!=SearchResult.ERRORNO.NO_ERROR) {
                    //没有检索到结果
                }

                //获取地理编码结果
            }

            @Override

            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {

                if (result!=null||result.error!=SearchResult.ERRORNO.NO_ERROR) {
                    //没有找到检索结果
                }

                //获取反向地理编码结果
                Intent intent = new Intent();
                intent.putExtra("location", GsonUtils.toJson(result));
                setResult(SUCCESS, intent);
                finish();

            }
        };
        mSearch.setOnGetGeoCodeResultListener(listener);
        mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                .location(latLng));
        mSearch.destroy();
    }
}

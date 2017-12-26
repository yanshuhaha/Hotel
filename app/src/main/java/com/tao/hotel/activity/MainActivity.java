package com.tao.hotel.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.tao.hotel.R;
import com.tao.hotel.bean.SearchBean;
import com.tao.hotel.maputlis.DefaultLocationClientOption;
import com.tao.hotel.maputlis.LocationCallBack;
import com.tao.hotel.maputlis.MyLocationListener;
import com.tao.hotel.utlis.DateUtils;
import com.tao.hotel.utlis.GlideImageLoader;
import com.tao.hotel.utlis.GsonUtils;
import com.tao.hotel.utlis.T;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final int LOCATION_CHOOSE = 0x11;
    private static final int EDIT_KEY = 0x12;

    @BindView(R.id.main_banner)
    Banner mainBanner;
    @BindView(R.id.tv_location_name)
    TextView tvLocationName;
    @BindView(R.id.tv_location_detailed)
    TextView tvLocationDetailed;
    @BindView(R.id.linear_my_location)
    LinearLayout linearMyLocation;
    @BindView(R.id.linear_location)
    LinearLayout linearLocation;
    @BindView(R.id.tv_check_time)
    TextView tvCheckTime;
    @BindView(R.id.tv_check_day)
    TextView tvCheckDay;
    @BindView(R.id.linear_check)
    LinearLayout linearCheck;
    @BindView(R.id.tv_time_interval)
    TextView tvTimeInterval;
    @BindView(R.id.tv_departure_time)
    TextView tvDepartureTime;
    @BindView(R.id.tv_departure_day)
    TextView tvDepartureDay;
    @BindView(R.id.linear_departure)
    LinearLayout linearDeparture;
    @BindView(R.id.linear_time)
    LinearLayout linearTime;
    @BindView(R.id.tv_keyword)
    TextView tvKeyword;
    @BindView(R.id.linear_keyword)
    LinearLayout linearKeyword;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.linear_price)
    LinearLayout linearPrice;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.iv_map)
    ImageView ivMap;
    @BindView(R.id.linear_search)
    LinearLayout linearSearch;
    @BindView(R.id.bt_my_order)
    Button btMyOrder;

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = null;

    private SearchBean bean = new SearchBean();


    @Override
    public int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        setBanner();
        initBaiduMap();
    }

    private void initBaiduMap() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        myListener = new MyLocationListener(mLocationClient, new LocationCallBack() {
            @Override
            public void success(BDLocation location) {
                ArrayList<String> list = new ArrayList<>();
                list.add(location.getLocationDescribe());
                list.add(location.getAddrStr());
                showMyLocation(list);
                bean.setLatLng(new LatLng(location.getLongitude(),location.getLatitude()));
            }

            @Override
            public void fail(String string) {
                T.show(MainActivity.this,"定位失败，请检查网络或者GPS", Toast.LENGTH_SHORT);
            }
        });
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.setLocOption(DefaultLocationClientOption.get());
    }

    private void setBanner() {
        mainBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mainBanner.setImages(getImages());
        //banner设置方法全部调用完毕时最后调用
        mainBanner.start();
    }

    private List<Integer> getImages() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.banner_1);
        images.add(R.drawable.banner_2);
        images.add(R.drawable.banner_3);
        images.add(R.drawable.banner_4);
        images.add(R.drawable.banner_5);
        return images;
    }


    @OnClick({R.id.linear_my_location, R.id.linear_check, R.id.linear_departure, R.id.linear_keyword,
            R.id.linear_price, R.id.bt_search, R.id.iv_map, R.id.bt_my_order,R.id.linear_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_location:
                goMap();
                break;
            case R.id.linear_my_location:
                getMyLocation();
                break;
            case R.id.linear_check:
                chooseTime(true);
                break;
            case R.id.linear_departure:
                chooseTime(false);
                break;
            case R.id.linear_keyword:
                editKeyWord();
                break;
            case R.id.linear_price:
                choosePrice();
                break;
            case R.id.bt_search:
                search();
                break;
            case R.id.iv_map:
                goHotelMap();
                break;
            case R.id.bt_my_order:
                goMyOrder();
                break;
        }
    }

    private void goHotelMap() {
        Intent intent = new Intent(MainActivity.this,HotelMapActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到我的订单
     */
    private void goMyOrder() {
        startActivity(new Intent(MainActivity.this,OrderListActivity.class));
    }

    /**
     * 跳转到地图
     */
    private void goMap() {
        startActivityForResult(new Intent(MainActivity.this,MapActivity.class),LOCATION_CHOOSE);
    }


    /**
     * 搜索
     */
    private void search() {
        if (bean.haveNull()){
            T.show(MainActivity.this,"请填写完整信息",Toast.LENGTH_SHORT);
            return;
        }
        Intent intent = new Intent(MainActivity.this,HotelActivity.class);
        intent.putExtra("searchBean",GsonUtils.toJson(bean));
        startActivity(intent);
        finish();
    }

    /**
     * 跳转选择价格界面
     */
    private void choosePrice() {

        List<String> date = new ArrayList<>();
        date.add("一星级 100 - 200");
        date.add("二星级 200 - 300");
        date.add("三星级 300 - 500");
        date.add("四星级 500 - 1000");
        date.add("五星级 1000 +");

        OptionsPickerView pvOptions = new  OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                tvPrice.setText(date.get(options1));
                bean.setLevel(options1);
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("星级")//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(0xff33b5e5)//标题文字颜色
                .setSubmitColor(0xff33b5e5)//确定按钮文字颜色
                .setCancelColor(0xff33b5e5)//取消按钮文字颜色
                .setTitleBgColor(0xFFFFFFFF)//标题背景颜色 Night mode
                .setBgColor(0xFFFFFFFF)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setLinkage(false)//设置是否联动，默认true
                .setLabels(null, null, null)//设置选择的三级单位
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(1, 1, 1)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .isDialog(true)//是否显示为对话框样式
                .build();

        pvOptions.setPicker(date, null, null);//添加数据源
        pvOptions.show();
    }

    /**
     * 跳转至关键字输入界面
     */
    private void editKeyWord() {
        startActivityForResult(new Intent(MainActivity.this,KeyWordActivity.class),EDIT_KEY);
    }

    /**
     * 跳转至时间选择dialog
     */
    private TimePickerView pvTime = null;
    private void chooseTime(boolean isCheck) {
        if (!isCheck && bean.getStartDate()==null){
            T.show(MainActivity.this,"请先选择入住日期",Toast.LENGTH_SHORT);
            return;
        }
        Calendar startDate = Calendar.getInstance();
        if (!isCheck){
            startDate.setTime(bean.getStartDate());
        }
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);
        endDate.set(2018,11,31);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (isCheck){
                    bean.setStartDate(date);
                    setCheckTime();
                }
                else{
                    bean.setEndDate(date);
                    setDepartureTime();
                    setTimeCha();
                }
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setRangDate(startDate,endDate)
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private void setTimeCha() {
        tvTimeInterval.setText(DateUtils.getSimpleDisTimeStr(bean.getStartDate(),bean.getEndDate()));
    }

    private void setDepartureTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bean.getEndDate());
        tvDepartureTime.setText(calendar.get(Calendar.MONTH)+1 + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
        tvDepartureDay.setText(DateUtils.getSimpleDisTimeStr(new Date(),bean.getEndDate()) + "后");
    }

    private void setCheckTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bean.getStartDate());
        tvCheckTime.setText(calendar.get(Calendar.MONTH)+1 + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
        tvCheckDay.setText(DateUtils.getSimpleDisTimeStr(new Date(),bean.getStartDate()) + "后");
    }

    /**
     * 通过textview展示自己所在的位置
     * @param myLocation 位置信息
     */
    private void showMyLocation(List<String> myLocation) {
        tvLocationName.setText(myLocation.get(0));
        tvLocationDetailed.setText(myLocation.get(1));
    }

    /**
     * 获取位置
     */
    public void getMyLocation() {
        mLocationClient.start();
        return;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOCATION_CHOOSE && resultCode == MapActivity.SUCCESS) {
            //Intent intent = getIntent();   // data 本身就是一个 Inten  所以不需要再new了 直接调用里面的方法就行了
            String s = data.getStringExtra("location");
            ReverseGeoCodeResult latLng = GsonUtils.jsonToBean(s,ReverseGeoCodeResult.class);
            ArrayList<String> location = new ArrayList<>();
            location.add(latLng.getSematicDescription());
            location.add(latLng.getAddress());
            showMyLocation(location);
            bean.setLatLng(latLng.getLocation());
        }
        if (requestCode == EDIT_KEY && resultCode == KeyWordActivity.SUCCESS){
            String s = data.getStringExtra("keyword");
            bean.setKeyWord(s);
            tvKeyword.setText(s);
        }

    }
}

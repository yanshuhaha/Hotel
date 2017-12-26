package com.tao.hotel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.tao.hotel.R;
import com.tao.hotel.bean.OrderBean;
import com.tao.hotel.bean.RoomBean;
import com.tao.hotel.utlis.DateUtils;
import com.tao.hotel.utlis.GsonUtils;
import com.tao.hotel.view.TimeLineLayout;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.iv_toolbar_order_back)
    ImageView ivToolbarOrderBack;
    @BindView(R.id.timeLineLayout_order)
    TimeLineLayout timeLineLayoutOrder;
    @BindView(R.id.tv_order_pay_status)
    TextView tvOrderPayStatus;
    @BindView(R.id.tv_order_pay_money)
    TextView tvOrderPayMoney;
    @BindView(R.id.tv_order_hotel_name)
    TextView tvOrderHotelName;
    @BindView(R.id.tv_order_hotel_location)
    TextView tvOrderHotelLocation;
    @BindView(R.id.tv_order_room_class)
    TextView tvOrderRoomClass;
    @BindView(R.id.tv_order_room_dir)
    TextView tvOrderRoomDir;
    @BindView(R.id.tv_order_together_time)
    TextView tvOrderTogetherTime;
    @BindView(R.id.bt_order_delete)
    Button btOrderDelete;
    @BindView(R.id.tv_order_start_time)
    TextView tvOrderStartTime;
    @BindView(R.id.tv_order_end_time)
    TextView tvOrderEndTime;

    private OrderBean orderBean;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_order;
    }

    @Override
    protected void init() {
        super.init();
        ArrayList<CharSequence> data = new ArrayList<>();
        data.add("提交订单");
        data.add("酒店确认");
        data.add("客户入住");
        data.add("客户离店");

        orderBean = GsonUtils.jsonToBean(getIntent().getStringExtra("orderBean"), OrderBean.class);
        tvOrderPayStatus.setText(data.get(orderBean.getState() - 1));
        //**
//        data.add(data.get(orderBean.getState() - 1) + "  <<<<");
        data.set(orderBean.getState()-1,data.get(orderBean.getState() - 1) + "  <<<<");
        timeLineLayoutOrder.setData(data);
        tvOrderPayMoney.setText("￥" + orderBean.getPrice());
        tvOrderHotelName.setText(orderBean.getHotelName());
        ReverseGeoCodeResult reverseGeoCodeResult = GsonUtils.jsonToBean(orderBean.getLocation(), ReverseGeoCodeResult.class);
        tvOrderHotelLocation.setText(reverseGeoCodeResult.getAddress());
        tvOrderRoomClass.setText(GsonUtils.jsonToBean(orderBean.getRooms(), RoomBean.class).getName());
        Date date1 = GsonUtils.jsonToBean(orderBean.getStartTime(), Date.class);
        tvOrderStartTime.setText("入住：" + DateUtils.timeDescribe(date1));
        Date date2 = GsonUtils.jsonToBean(orderBean.getEndTime(),Date.class);
        tvOrderEndTime.setText("离店：" + DateUtils.timeDescribe(date2));
        tvOrderTogetherTime.setText("共" + DateUtils.getSimpleDisTimeStr(date1,date2));

    }

    @OnClick({ R.id.bt_order_delete, R.id.iv_toolbar_order_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_order_delete:
                break;
            case R.id.iv_toolbar_order_back:
                break;
        }
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar.with(this)
                .statusBarColor(R.color.background)
                .init();
    }

}

package com.tao.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tao.hotel.R;
import com.tao.hotel.bean.OrderBean;
import com.tao.hotel.bean.RoomBean;
import com.tao.hotel.utlis.DateUtils;
import com.tao.hotel.utlis.GsonUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CashierActivity extends BaseActivity {

    @BindView(R.id.iv_cashier_back)
    ImageView ivCashierBack;
    @BindView(R.id.toolbar_cashier)
    Toolbar toolbarCashier;
    @BindView(R.id.tv_time_cashier)
    TextView tvTimeCashier;
    @BindView(R.id.tv_cashier_hotel_name)
    TextView tvCashierHotelName;
    @BindView(R.id.tv_cashier_room_class)
    TextView tvCashierRoomClass;
    @BindView(R.id.tv_cashier_person_name)
    TextView tvCashierPersonName;
    @BindView(R.id.tv_cashier_check_time)
    TextView tvCashierCheckTime;
    @BindView(R.id.tv_cashier_departure_time)
    TextView tvCashierDepartureTime;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.tv_cashier_pay)
    TextView tvCashierPay;
    @BindView(R.id.relative_wechat)
    RelativeLayout relativeWechat;
    @BindView(R.id.relative_alipay)
    RelativeLayout relativeAlipay;
    @BindView(R.id.iv_cashier_pay_wechat)
    ImageView ivCashierPayWechat;
    @BindView(R.id.iv_cashier_pay_alipay)
    ImageView ivCashierPayAlipay;
    @BindView(R.id.tv_cashier_money)
    TextView tvCashierMoney;

    private OrderBean orderBean;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_cashier;
    }

    @Override
    protected void init() {
        super.init();
        orderBean = GsonUtils.jsonToBean(getIntent().getStringExtra("orderBean"), OrderBean.class);
        tvCashierHotelName.setText(orderBean.getHotelName());
        tvCashierPersonName.setText(orderBean.getPersonName());
        tvCashierRoomClass.setText(GsonUtils.jsonToBean(orderBean.getRooms(), RoomBean.class).getName());
        Date date = GsonUtils.jsonToBean(orderBean.getStartTime(), Date.class);
        tvCashierCheckTime.setText(DateUtils.timeDescribe(date));
        date = GsonUtils.jsonToBean(orderBean.getEndTime(), Date.class);
        tvCashierDepartureTime.setText(DateUtils.timeDescribe(date));
        tvCashierMoney.setText("￥" + orderBean.getPrice());
    }

    @OnClick({R.id.iv_cashier_back, R.id.tv_cashier_pay, R.id.relative_wechat, R.id.relative_alipay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cashier_back:
                finish();
                break;
            case R.id.tv_cashier_pay:
                orderBean.setState(2);
                orderBean.save();
                Intent intent = new Intent(CashierActivity.this, OrderActivity.class);
                intent.putExtra("orderBean", GsonUtils.toJson(orderBean));
                startActivity(intent);
                finish();
                break;
            case R.id.relative_wechat:
                ivCashierPayAlipay.setVisibility(View.INVISIBLE);
                ivCashierPayWechat.setVisibility(View.VISIBLE);
                break;
            case R.id.relative_alipay:
                ivCashierPayAlipay.setVisibility(View.VISIBLE);
                ivCashierPayWechat.setVisibility(View.INVISIBLE);
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

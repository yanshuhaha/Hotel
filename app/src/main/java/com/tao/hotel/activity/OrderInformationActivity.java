package com.tao.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tao.hotel.R;
import com.tao.hotel.bean.OrderBean;
import com.tao.hotel.bean.RoomBean;
import com.tao.hotel.utlis.DateUtils;
import com.tao.hotel.utlis.GsonUtils;
import com.tao.hotel.utlis.RegularUtils;
import com.tao.hotel.utlis.T;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInformationActivity extends BaseActivity {

    @BindView(R.id.iv_toolbar_back_order)
    ImageView ivToolbarBackOrder;
    @BindView(R.id.tv_toolbar_order_name)
    TextView tvToolbarOrderName;
    @BindView(R.id.tv_order_information_room_name)
    TextView tvOrderInformationRoomName;
    @BindView(R.id.tv_order_information_room_content)
    TextView tvOrderInformationRoomContent;
    @BindView(R.id.tv_order_room_number)
    TextView tvOrderRoomNumber;
    @BindView(R.id.ed_order_person_name)
    EditText edOrderPersonName;
    @BindView(R.id.ed_order_person_phone)
    EditText edOrderPersonPhone;
    @BindView(R.id.tv_order_tv1)
    TextView tvOrderTv1;
    @BindView(R.id.tv_order_information_payment)
    TextView tvOrderInformationPayment;
    @BindView(R.id.tv_order_information_payment_money)
    TextView tvOrderInformationPaymentMoney;

    private OrderBean orderBean;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_order_information;
    }

    @Override
    protected void init() {
        super.init();
        orderBean = GsonUtils.jsonToBean(getIntent().getStringExtra("orderBean"), OrderBean.class);
        Log.i("1111", "init: " + orderBean.toString());
        tvToolbarOrderName.setText(orderBean.getHotelName());
        tvOrderInformationRoomName.setText(GsonUtils.jsonToBean(orderBean.getRooms(), RoomBean.class).getName());
        StringBuffer buffer = new StringBuffer();
        buffer.append("入住：");
        Date date1 = GsonUtils.jsonToBean(orderBean.getStartTime(), Date.class);
        buffer.append(DateUtils.timeDescribe(date1));
        buffer.append(" 离店:");
        Date date2 = GsonUtils.jsonToBean(orderBean.getEndTime(), Date.class);
        buffer.append(DateUtils.timeDescribe(date2));
        buffer.append(" 共" + DateUtils.getSimpleDisTimeStr(date1, date2) + "\n");
        buffer.append("床型：" + GsonUtils.jsonToBean(orderBean.getRooms(), RoomBean.class).getBedDir());
        buffer.append(" 早餐：无 宽带：免费");
        tvOrderInformationRoomContent.setText(buffer.toString());
        tvOrderInformationPaymentMoney.setText("￥" + orderBean.getPrice());

    }

    @OnClick(R.id.tv_order_information_payment)
    public void onViewClicked() {
        orderBean.setPersonName(edOrderPersonName.getText().toString().trim());
        if (RegularUtils.isMobileExact(edOrderPersonPhone.getText().toString())) {
            orderBean.setPersonPhone(edOrderPersonPhone.getText().toString().trim());
            Intent intent = new Intent(OrderInformationActivity.this, CashierActivity.class);
            intent.putExtra("orderBean", GsonUtils.toJson(orderBean));
            startActivity(intent);
            finish();
        } else {
            T.show(OrderInformationActivity.this, "手机格式不正确", Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar.with(this)
                .statusBarColor(R.color.background)
                .init();
    }
}

package com.tao.hotel.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.tao.hotel.R;
import com.tao.hotel.adapter.RoomAdapter;
import com.tao.hotel.bean.HotelBean;
import com.tao.hotel.bean.RoomBean;
import com.tao.hotel.model.Hotelimpl;
import com.tao.hotel.model.Roomimpl;
import com.tao.hotel.utlis.DateUtils;
import com.tao.hotel.utlis.T;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class RoomActivity extends BaseActivity {

    private static final String TAG = "RoomActivity";
    
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_toolbar_name)
    TextView tvToolbarName;
    @BindView(R.id.iv_toolbar_collection)
    ImageView ivToolbarCollection;
    @BindView(R.id.iv_toolbar_share)
    ImageView ivToolbarShare;
    @BindView(R.id.tv_room_check_time)
    TextView tvRoomCheckTime;
    @BindView(R.id.linear_check_time)
    LinearLayout linearCheckTime;
    @BindView(R.id.tv_room_departure_time)
    TextView tvRoomDepartureTime;
    @BindView(R.id.linear_departure_time)
    LinearLayout linearDepartureTime;
    @BindView(R.id.rv_room)
    RecyclerView rvRoom;

    private TimePickerView pvTime = null;
    private Date checkDate = null;
    private Date departureDate = null;
    private HotelBean bean ;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_room;
    }

    @Override
    protected void init() {
        super.init();

        int id = getIntent().getIntExtra("hotelId",0);
        Log.i(TAG, "init: " +id);
        bean = new Hotelimpl().queryByHotelId(id + "");
        tvToolbarName.setText(bean.getName());

        List<RoomBean> list = new Roomimpl().queryByHotelId(id + "");
        RoomAdapter adapter = new RoomAdapter(RoomActivity.this,list);
        rvRoom.setLayoutManager(new LinearLayoutManager(RoomActivity.this));
        rvRoom.setAdapter(adapter);
    }

    @OnClick({R.id.iv_toolbar_back, R.id.iv_toolbar_collection, R.id.iv_toolbar_share, R.id.linear_check_time, R.id.linear_departure_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_back:
                finish();
                break;
            case R.id.iv_toolbar_collection:
                break;
            case R.id.iv_toolbar_share:
                break;
            case R.id.linear_check_time:
                chooseTime(true);
                break;
            case R.id.linear_departure_time:
                chooseTime(false);
                break;
        }
    }

    private void chooseTime(boolean isCheck) {
        if (!isCheck && checkDate==null){
            T.show(RoomActivity.this,"请先选择入住日期", Toast.LENGTH_SHORT);
            return;
        }
        Calendar startDate = Calendar.getInstance();
        if (!isCheck){
            startDate.setTime(checkDate);
        }
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);
        endDate.set(2018,11,31);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (isCheck){
                    checkDate = date;
                    setTime(tvRoomCheckTime,date);
                }
                else{
                    departureDate = date;
                    setTime(tvRoomDepartureTime,date);
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

    private void setTime(TextView textView , Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        textView.setText(calendar.get(Calendar.MONTH)+1 + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar.with(this)
                .statusBarColor(R.color.blue_1)
                .init();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public HotelBean getBean() {
        return bean;
    }
}

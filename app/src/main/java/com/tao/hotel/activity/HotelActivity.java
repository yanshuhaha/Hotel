package com.tao.hotel.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tao.hotel.R;
import com.tao.hotel.adapter.HotelAdapter;
import com.tao.hotel.model.Hotelimpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelActivity extends BaseActivity {

    @BindView(R.id.rv_hotel)
    RecyclerView rvHotel;

    private HotelAdapter adapter ;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_hotel;
    }

    @Override
    protected void init() {
        super.init();
        adapter =  new HotelAdapter(HotelActivity.this, new Hotelimpl().queryAll());
        rvHotel.setAdapter(adapter);
        rvHotel.setLayoutManager(new LinearLayoutManager(HotelActivity.this));
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar.with(this)
                .statusBarColor(R.color.background)
                .init();
    }

}

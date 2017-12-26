package com.tao.hotel.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import com.tao.hotel.R;
import com.tao.hotel.activity.RoomActivity;
import com.tao.hotel.bean.HotelBean;
import java.util.List;

/**
 * @author: Tao
 * @time: 2017/12/17
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public class HotelAdapter extends SimpleAdapter<HotelBean> {
    private Context context;

    private TextView tvItemName;
    private TextView tvItemPrice;
    private TextView tvItemScore;
    private TextView tvItemLocation;
    private TextView tvItemContent;

    public HotelAdapter(Context context, List<HotelBean> data) {
        super(context, data, R.layout.item_hotel);
        this.context = context;
    }

    @Override
    protected void change(BaseViewHolder viewHolder, HotelBean hotelBean) {
        tvItemName = viewHolder.findView(R.id.tv_item_name);
        tvItemPrice = viewHolder.findView(R.id.tv_item_price);
        tvItemScore = viewHolder.findView(R.id.tv_item_score);
        tvItemLocation = viewHolder.findView(R.id.tv_item_location);
        tvItemContent = viewHolder.findView(R.id.tv_item_content);

        tvItemName.setText(hotelBean.getName());
        tvItemPrice.setText(hotelBean.getPrice() + "");
        tvItemScore.setText(hotelBean.getScore() + "");
        tvItemLocation.setText(hotelBean.getLocationDir());
        tvItemContent.setOnClickListener(view -> goActivitty(hotelBean.getHotelId()));
    }

    private void goActivitty(int id) {
        Intent intent = new Intent(context, RoomActivity.class);
        intent.putExtra("hotelId",id);
        context.startActivity(intent);
    }

}

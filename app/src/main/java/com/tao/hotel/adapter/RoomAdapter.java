package com.tao.hotel.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tao.hotel.R;
import com.tao.hotel.activity.OrderInformationActivity;
import com.tao.hotel.activity.RoomActivity;
import com.tao.hotel.bean.HotelBean;
import com.tao.hotel.bean.OrderBean;
import com.tao.hotel.bean.RoomBean;
import com.tao.hotel.utlis.GsonUtils;
import com.tao.hotel.utlis.T;

import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * @author: Tao
 * @time: 2017/12/20
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public class RoomAdapter extends SimpleAdapter<RoomBean> {

    private ImageView ivRoomItem;
    private TextView tvRoomItemName;
    private TextView tvRoomItemPlane;
    private TextView tvRoomItemSatisfaction;
    private TextView tvRoomItemPrice;
    private TextView tvRoomItemReserve;

    public RoomAdapter(Context context, List<RoomBean> data) {
        super(context, data, R.layout.item_room);
    }

    @Override
    protected void change(BaseViewHolder viewHolder, RoomBean bean) {
        ivRoomItem = viewHolder.findView(R.id.iv_room_item);
        tvRoomItemName = viewHolder.findView(R.id.tv_room_item_name);
        tvRoomItemPlane = viewHolder.findView(R.id.tv_room_item_plane);
        tvRoomItemSatisfaction = viewHolder.findView(R.id.tv_room_item_satisfaction);
        tvRoomItemPrice = viewHolder.findView(R.id.tv_room_item_price);
        tvRoomItemReserve = viewHolder.findView(R.id.tv_room_item_reserve);

        ivRoomItem.setImageResource(bean.getImage());
        tvRoomItemName.setText(bean.getName());
        tvRoomItemPlane.setText(bean.getPlane() + "m² " + bean.getBedDir() + " " +
                bean.getComment() + "点评");
        tvRoomItemSatisfaction.setText("预定满意度" + bean.getSatisfaction() + "%");
        tvRoomItemPrice.setText("¥"+(int)bean.getPrice());
        tvRoomItemReserve.setOnClickListener(view -> goCreatOrder(bean));
    }

    private void goCreatOrder(RoomBean bean) {
        if (((RoomActivity)mContext).getCheckDate() == null ||
                ((RoomActivity)mContext).getDepartureDate() == null){
            T.show(mContext,"请选择日期", Toast.LENGTH_SHORT);
            return;
        }
        HotelBean hotelbean = ((RoomActivity)mContext).getBean();
        OrderBean orderBean = new OrderBean();
        orderBean.setHotelName(hotelbean.getName());
        orderBean.setHotelId(hotelbean.getHotelId());
        orderBean.setStartTime(GsonUtils.toJson(((RoomActivity)mContext).getCheckDate()));
        orderBean.setEndTime(GsonUtils.toJson(((RoomActivity)mContext).getDepartureDate()));
        orderBean.setLocation(hotelbean.getLocation());
        orderBean.setRoomNum(1);
        orderBean.setRooms(GsonUtils.toJson(bean));
        orderBean.setPrice(bean.getPrice());
        Intent intent = new Intent(mContext, OrderInformationActivity.class);
        intent.putExtra("orderBean",GsonUtils.toJson(orderBean));
        mContext.startActivity(intent);
    }
}

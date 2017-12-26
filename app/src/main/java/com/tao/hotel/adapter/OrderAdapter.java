package com.tao.hotel.adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tao.hotel.R;
import com.tao.hotel.bean.OrderBean;
import com.tao.hotel.bean.RoomBean;
import com.tao.hotel.utlis.DateUtils;
import com.tao.hotel.utlis.GsonUtils;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * @author: Tao
 * @time: 2017/12/25
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public class OrderAdapter extends SimpleAdapter<OrderBean> {

    private ImageView ivItemOrderListHotel;
    private TextView tvItemOrderListHotelName;
    private TextView tvItemOrderListPrice;
    private TextView tvItemOrderListPriceState;
    private TextView tvItemOrderListCheckTime;
    private TextView tvItemOrderListLastTime;
    private Button btItemOrderListDelete;
    private Button btItemOrderListPay;

    public OrderAdapter(Context context, List<OrderBean> data) {
        super(context, data, R.layout.item_order);
    }

    @Override
    protected void change(BaseViewHolder viewHolder, OrderBean orderBean) {
        ivItemOrderListHotel = viewHolder.findView(R.id.iv_item_order_list_hotel);
        tvItemOrderListHotelName = viewHolder.findView(R.id.tv_item_order_list_hotel_name);
        tvItemOrderListPrice = viewHolder.findView(R.id.tv_item_order_list_price);
        tvItemOrderListPriceState = viewHolder.findView(R.id.tv_item_order_list_price_state);
        tvItemOrderListCheckTime = viewHolder.findView(R.id.tv_item_order_list_check_time);
        tvItemOrderListLastTime = viewHolder.findView(R.id.tv_item_order_list_last_time);
        btItemOrderListDelete = viewHolder.findView(R.id.bt_item_order_list_delete);
        btItemOrderListPay = viewHolder.findView(R.id.bt_item_order_list_pay);

        RoomBean bean = GsonUtils.jsonToBean(orderBean.getRooms(),RoomBean.class);
        tvItemOrderListHotelName.setText(bean.getName());
        tvItemOrderListPrice.setText(bean.getPrice() + "");
        tvItemOrderListPrice.setText("已支付");
        Date checkDate = GsonUtils.jsonToBean(orderBean.getStartTime(),Date.class);
        tvItemOrderListCheckTime.setText("入住时间:" + DateUtils.timeDescribe(checkDate));
        tvItemOrderListLastTime.setText("入住时间:" + DateUtils.timeDescribe(checkDate) + " 20:00");
        
        btItemOrderListDelete.setOnClickListener(view -> delete());
        btItemOrderListPay.setOnClickListener(view -> pay());

    }

    private void pay() {
    }

    private void delete() {
    }
}

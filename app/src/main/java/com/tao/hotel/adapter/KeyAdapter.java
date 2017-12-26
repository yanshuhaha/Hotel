package com.tao.hotel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;

import com.tao.hotel.R;

import java.util.List;

/**
 * @author: Tao
 * @time: 2017/12/17
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public class KeyAdapter extends SimpleAdapter<String> {
    private EditText editText ;

    public KeyAdapter(Context context, List data , EditText editText) {
        super(context, data, R.layout.item_key);
        this.editText = editText ;
    }

    @Override
    protected void change(BaseViewHolder viewHolder, String o) {
        TextView textView = viewHolder.findView(R.id.tv_item_key);
        textView.setText(o);
        textView.setOnClickListener(v -> editText.setText(o) );
    }
}

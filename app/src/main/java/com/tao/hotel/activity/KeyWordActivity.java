package com.tao.hotel.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tao.hotel.R;
import com.tao.hotel.adapter.KeyAdapter;
import com.tao.hotel.utlis.T;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import butterknife.BindView;

public class KeyWordActivity extends BaseActivity {

    public static final int SUCCESS = 0x1;

    @BindView(R.id.ed_key)
    EditText edKey;
    @BindView(R.id.rv_key)
    RecyclerView rvKey;
    @BindView(R.id.iv_key_go)
    ImageView imagego;


    private KeyAdapter adapter ;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_key_word;
    }

    @Override
    protected void init() {
        super.init();
        adapter = new KeyAdapter(KeyWordActivity.this, getDate(),edKey);
        rvKey.setAdapter(adapter);
        rvKey.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        rvKey.setItemAnimator(new DefaultItemAnimator());
        imagego.setOnClickListener(v -> rvGo());
    }

    private void rvGo() {
        if (edKey.getText().toString().isEmpty()){
            T.show(KeyWordActivity.this,"请输入", Toast.LENGTH_SHORT);
        }else if (edKey.getText().toString().equals("456")){
            Intent intent = new Intent(KeyWordActivity.this,DateActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent();
            intent.putExtra("keyword",edKey.getText().toString().trim());
            setResult(SUCCESS, intent);
            finish();
        }
    }

    private List getDate() {
        List<String> list = new ArrayList<>();
        list.add("广州");
        list.add("北京");
        list.add("维也纳");
        list.add("锦江之星");
        list.add("汉庭酒店");
        list.add("如家");
        list.add("七天连锁");
        list.add("情趣");
        list.add("情侣");
        list.add("经济");
        list.add("秘密幽会");
        list.add("五星超豪华");
        list.add("CosPlay");
        return list;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();

    }
}

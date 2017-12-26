package com.tao.hotel.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.gyf.barlibrary.ImmersionBar;
import com.tao.hotel.utlis.L;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: Tao
 * @time: 2017/12/8
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 基础activity，封装沉浸式标题栏以及黄油刀
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResID());
        L.i(this.getClass().getSimpleName(),"onCreate");
        unbinder = ButterKnife.bind(this);
        if (isImmersionBarEnabled())
            initImmersionBar();
        //初始化
        init();
    }

    /**
     * 设置布局
     * @return 布局文件id
     */
    public abstract int setLayoutResID();
    /**
     * 初始化数据
     */
    protected void init(){}
    /**
     * 初始化状态栏
     */
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    /**
     * 设置是否启用沉浸式
     * @return
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }

    @Override
    public void finish() {
        super.finish();
    }
}

package com.tao.hotel.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tao.hotel.R;
import com.tao.hotel.adapter.FragmentAdapter;
import com.tao.hotel.fragment.OnFragmentInteractionListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListActivity extends BaseActivity implements
        OnFragmentInteractionListener {

    @BindView(R.id.tabLayout_order_list)
    TabLayout tabLayoutOrderList;
    @BindView(R.id.viewPager_order_list)
    ViewPager viewPagerOrderList;

    private FragmentAdapter adapter ;

    private TabLayout.Tab one ;
    private TabLayout.Tab two ;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void init() {
        super.init();
        initView();
    }

    private void initView() {
        adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPagerOrderList.setAdapter(adapter);
        tabLayoutOrderList.setupWithViewPager(viewPagerOrderList);
        one = tabLayoutOrderList.getTabAt(0);
        two = tabLayoutOrderList.getTabAt(1);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar.with(this)
                .statusBarColor(R.color.background)
                .init();
    }

}

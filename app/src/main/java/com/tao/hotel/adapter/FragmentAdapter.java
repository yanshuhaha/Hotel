package com.tao.hotel.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tao.hotel.fragment.AgeFragment;
import com.tao.hotel.fragment.NowFragment;

/**
 * @author: Tao
 * @time: 2017/12/24
 * @e-mail: 1462320178@qq.com
 * @version: 1.0
 * @exception: 无
 * @explain: 说明
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"三个月内", "三个月前"};

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return AgeFragment.newInstance("测试11","测试12");
        }
        return NowFragment.newInstance("测试21","测试22");
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}

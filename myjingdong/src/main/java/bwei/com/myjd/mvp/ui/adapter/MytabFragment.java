package bwei.com.myjd.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Jack Lee on 2018/7/25.
 * name:Juck Lee
 */

public class MytabFragment extends FragmentPagerAdapter {

    private List<Fragment> list;
    private String[] title = {"全部","待付款","已付款","已取消"};

    public MytabFragment(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}

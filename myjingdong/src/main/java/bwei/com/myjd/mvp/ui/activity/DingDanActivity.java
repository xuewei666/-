package bwei.com.myjd.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.DingDanBean;
import bwei.com.myjd.mvp.ui.adapter.MytabFragment;
import bwei.com.myjd.mvp.ui.fragment.MyViewPager;
import bwei.com.myjd.mvp.ui.tabfragment.FragmentDFK;
import bwei.com.myjd.mvp.ui.tabfragment.FragmentQB;
import bwei.com.myjd.mvp.ui.tabfragment.FragmentYQX;
import bwei.com.myjd.mvp.ui.tabfragment.FragmentYZU;

public class DingDanActivity extends AppCompatActivity {

    @BindView(R.id.tb_layout)
    TabLayout tbLayout;
    @BindView(R.id.my_viewpager)
    MyViewPager myViewpager;
    @BindView(R.id.tb_head01)
    Toolbar tbHead01;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dingdan);

        ButterKnife.bind(this);
        setSupportActionBar(tbHead01);
        tbHead01.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setTitle("订单");
        List<Fragment> list = new ArrayList<>();
        Intent intent = getIntent();
        List<DingDanBean> list1 = (List<DingDanBean>) intent.getSerializableExtra("list");
        list.add(new FragmentQB(list1));
        list.add(new FragmentDFK());
        list.add(new FragmentYZU());
        list.add(new FragmentYQX());
        //tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //创建适配器
        tbLayout.setTabMode(TabLayout.MODE_FIXED);
        MytabFragment mytabFragment = new MytabFragment(getSupportFragmentManager(), list);
        myViewpager.setAdapter(mytabFragment);
        tbLayout.setupWithViewPager(myViewpager);


    }

}

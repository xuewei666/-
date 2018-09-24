package bwei.com.myjd.mvp.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.myjd.R;
import bwei.com.myjd.mvp.ui.adapter.MyPageAdapter;
import bwei.com.myjd.mvp.ui.fragment.FaXianFragment;
import bwei.com.myjd.mvp.ui.fragment.FenLeiFragment;
import bwei.com.myjd.mvp.ui.fragment.GouWuCheFragment;
import bwei.com.myjd.mvp.ui.fragment.HomeFragment;
import bwei.com.myjd.mvp.ui.fragment.MainFragment;
import bwei.com.myjd.mvp.ui.fragment.WoDeFragment;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.btn1)
    RadioButton btn1;
    @BindView(R.id.btn2)
    RadioButton btn2;
    @BindView(R.id.btn3)
    RadioButton btn3;
    @BindView(R.id.btn4)
    RadioButton btn4;
    @BindView(R.id.btn5)
    RadioButton btn5;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final List<Fragment> list = new ArrayList<>();
//        list.add(new MainFragment());
        list.add(new HomeFragment());
        list.add(new FenLeiFragment());
        list.add(new FaXianFragment());
        list.add(new GouWuCheFragment());
        list.add(new WoDeFragment());
        MyPageAdapter myPageAdapter = new MyPageAdapter(getSupportFragmentManager(), list);

        viewPager.setAdapter(myPageAdapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    radioGroup.check(radioGroup.getChildAt(position%list.size()).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.btn2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.btn3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.btn4:
                viewPager.setCurrentItem(3);
                break;
            case R.id.btn5:
                viewPager.setCurrentItem(4);
                break;
        }
    }
}

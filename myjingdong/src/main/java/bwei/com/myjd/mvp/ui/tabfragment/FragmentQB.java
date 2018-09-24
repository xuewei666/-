package bwei.com.myjd.mvp.ui.tabfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwei.com.myjd.BuildConfig;
import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.DingDanBean;
import bwei.com.myjd.mvp.ui.adapter.TobAdapter;


/**
 * Created by Jack Lee on 2018/7/25.
 */

@SuppressLint("ValidFragment")
public class FragmentQB extends Fragment {


    @BindView(R.id.tob01_recyler)
    RecyclerView tob01Recyler;
    Unbinder unbinder;
    List<DingDanBean> list1;
    @SuppressLint("ValidFragment")
    public FragmentQB(List<DingDanBean> list1) {

         Log.e("FragmentQB", "list1:" + list1.toString());
        this.list1=list1;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.layout_tob01, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        tob01Recyler.setLayoutManager(linearLayoutManager);
        TobAdapter tobAdapter = new TobAdapter(R.layout.tob01_item, list1);
        tob01Recyler.setAdapter(tobAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

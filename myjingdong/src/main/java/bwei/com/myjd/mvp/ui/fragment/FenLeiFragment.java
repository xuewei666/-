package bwei.com.myjd.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import bwei.com.myjd.R;
import bwei.com.myjd.di.component.DaggerFenLeiComponent;
import bwei.com.myjd.di.module.FenLeiModule;
import bwei.com.myjd.mvp.contract.FenLeiContract;
import bwei.com.myjd.mvp.data.FenLeiBean;
import bwei.com.myjd.mvp.data.ShouYeFLBean;
import bwei.com.myjd.mvp.presenter.FenLeiPresenter;
import bwei.com.myjd.mvp.ui.adapter.SetYouAdapter;
import bwei.com.myjd.mvp.ui.adapter.ZuoAdapter;


import static com.jess.arms.utils.Preconditions.checkNotNull;


public class FenLeiFragment extends BaseFragment<FenLeiPresenter> implements FenLeiContract.View {

    @BindView(R.id.zrv)
    RecyclerView zrv;
    @BindView(R.id.yev)
    ExpandableListView yev;
    Unbinder unbinder;

    public static FenLeiFragment newInstance() {
        FenLeiFragment fragment = new FenLeiFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerFenLeiComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .fenLeiModule(new FenLeiModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fen_lei, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.getZuoData();
        mPresenter.getYouData(1);
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void responseZuoMsg(ShouYeFLBean shouYeFLBean) {
        List<ShouYeFLBean.DataBean> data = shouYeFLBean.getData();
        //线性布局
        zrv.setLayoutManager(new LinearLayoutManager(getContext()));
        ZuoAdapter zuoAdapter = new ZuoAdapter(R.layout.layout_zuo,data);
        zrv.setAdapter(zuoAdapter);
        zuoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                int cid = data.get(position).getCid();
                mPresenter.getYouData(cid);

            }
        });

    }

    @Override
    public void responseYouMsg(FenLeiBean fenLeiBean) {
        List<FenLeiBean.DataBean> data = fenLeiBean.getData();
        SetYouAdapter setYouAdapter = new SetYouAdapter(getActivity(), data);
        yev.setAdapter(setYouAdapter);
        //父级列表默认全部展开
        int groupCount = yev.getCount();
        for (int i=0; i<groupCount; i++)
        {
            yev.expandGroup(i);
        }

    }


}

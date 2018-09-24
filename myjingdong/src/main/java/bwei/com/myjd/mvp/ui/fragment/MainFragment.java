package bwei.com.myjd.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.recker.flybanner.FlyBanner;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwei.com.myjd.R;
import bwei.com.myjd.di.component.DaggerMainComponent;
import bwei.com.myjd.di.module.MainModule;
import bwei.com.myjd.mvp.contract.MainContract;
import bwei.com.myjd.mvp.data.LunBoUserBean;
import bwei.com.myjd.mvp.data.ShouYeFLBean;
import bwei.com.myjd.mvp.presenter.MainPresenter;
import bwei.com.myjd.mvp.ui.adapter.MYTJAdapter;
import bwei.com.myjd.mvp.ui.adapter.MyJdmxAdapte;
import bwei.com.myjd.mvp.ui.adapter.SYFLAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainFragment extends BaseFragment<MainPresenter> implements MainContract.View {


    @BindView(R.id.erweima)
    ImageView erweima;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.xiaoxi)
    ImageView xiaoxi;
    @BindView(R.id.flybanner)
    FlyBanner flybanner;
    @BindView(R.id.smarter_fresh)
    SmartRefreshLayout smarterFresh;
    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.JDMX_view)
    RecyclerView JDMXView;
    @BindView(R.id.tuijian)
    RecyclerView tuijian;
//    private RecyclerView rv_recommend;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
       /* // 使通知栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//FLAG_TRANSLUCENT_STATUS
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
           // getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }*/

        List<String> info = new ArrayList<>();
        info.add("欢迎访问京东app");
        info.add("大家有没有在 听课");
        info.add("是不是还有人在睡觉");
        info.add("你妈妈在旁边看着呢");
        info.add("赶紧的好好学习吧 马上毕业了");
        info.add("你没有事件睡觉了");
        marqueeView.startWithList(info);
        mPresenter.getData();
        mPresenter.getFLData();

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
    public void responseMsg(LunBoUserBean lunBoUserBean) {
        List<LunBoUserBean.DataBean> data = lunBoUserBean.getData();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getIcon());

        }
        flybanner.setImagesUrl(list);
        List<LunBoUserBean.MiaoshaBean.ListBeanX> list1 = lunBoUserBean.getMiaosha().getList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);

        JDMXView.setLayoutManager(gridLayoutManager);
        MyJdmxAdapte myJdmxAdapte = new MyJdmxAdapte(R.layout.layout_jdmx, list1);
        JDMXView.setAdapter(myJdmxAdapte);

        TuiJianData(lunBoUserBean);
    }

    private void TuiJianData(LunBoUserBean lunBoUserBean) {
        List<LunBoUserBean.TuijianBean.ListBean> list = lunBoUserBean.getTuijian().getList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
//        rv_recommend.setLayoutManager(gridLayoutManager);
        MYTJAdapter mytjAdapter = new MYTJAdapter(R.layout.layout_tuijian, list);
        //mytjAdapter.addHeaderView(JDMXView);
//        rv_recommend.setAdapter(mytjAdapter);

    }

    @Override
    public void error(String s) {
        Log.e("MainFragment", s);
    }

    @Override
    public void responseFLMsg(ShouYeFLBean shouYeFLBean) {
        List<ShouYeFLBean.DataBean> data = shouYeFLBean.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        SYFLAdapter syflAdapter = new SYFLAdapter(R.layout.layout_shouyefenlei, data);
        recyclerView.setAdapter(syflAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

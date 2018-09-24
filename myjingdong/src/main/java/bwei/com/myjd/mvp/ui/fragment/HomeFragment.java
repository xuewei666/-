package bwei.com.myjd.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.recker.flybanner.FlyBanner;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import bwei.com.myjd.R;
import bwei.com.myjd.di.component.DaggerHomeComponent;
import bwei.com.myjd.di.module.HomeModule;
import bwei.com.myjd.mvp.contract.HomeContract;
import bwei.com.myjd.mvp.data.LunBoUserBean;
import bwei.com.myjd.mvp.data.ShouYeFLBean;
import bwei.com.myjd.mvp.presenter.HomePresenter;
import bwei.com.myjd.mvp.ui.activity.SPXQActivity;
import bwei.com.myjd.mvp.ui.activity.SouSuoActivity;
import bwei.com.myjd.mvp.ui.adapter.MYTJAdapter;
import bwei.com.myjd.mvp.ui.adapter.MyJdmxAdapte;
import bwei.com.myjd.mvp.ui.adapter.SYFLAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    @BindView(R.id.sousuo)
    TextView sousuo;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    Unbinder unbinder;
    @BindView(R.id.zxing)
    ImageView zxing;
    private RecyclerView recycler_view;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        final RefreshLayout refreshLayout = (RefreshLayout) rootView.findViewById(R.id.srl_refresh);
        //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new WaveSwipeHeader(getContext()));
        //设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        return rootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


        mPresenter.getData();


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
    public void error(String s) {

    }

    @Override
    public void responseMsg(LunBoUserBean lunBoUserBean) {

        View header_view = LayoutInflater.from(getActivity()).inflate(R.layout.header_view, null);
        mPresenter.getFLData(header_view);
        FlyBanner flybanner = header_view.findViewById(R.id.flybanner);
        List<LunBoUserBean.DataBean> data = lunBoUserBean.getData();
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list1.add(data.get(i).getIcon());
        }
        flybanner.setImagesUrl(list1);

        List<LunBoUserBean.MiaoshaBean.ListBeanX> list2 = lunBoUserBean.getMiaosha().getList();
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        RecyclerView JDMXView = header_view.findViewById(R.id.JDMX_view);
        JDMXView.setLayoutManager(gridLayoutManager1);
        MyJdmxAdapte myJdmxAdapte = new MyJdmxAdapte(R.layout.layout_jdmx, list2);
        JDMXView.setAdapter(myJdmxAdapte);

        List<LunBoUserBean.TuijianBean.ListBean> list = lunBoUserBean.getTuijian().getList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        rvRecommend.setLayoutManager(gridLayoutManager);
        MYTJAdapter mytjAdapter = new MYTJAdapter(R.layout.layout_tuijian, list);
        MyHeader(header_view);
        rvRecommend.setAdapter(mytjAdapter);
        mytjAdapter.addHeaderView(header_view);

        mytjAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int pid = list.get(position).getPid();
                Intent intent = new Intent(getActivity(), SPXQActivity.class);
                intent.putExtra("pid", pid + "");
                startActivity(intent);
            }
        });
    }


    private void MyHeader(View header_view) {
        MarqueeView marqueeView = header_view.findViewById(R.id.marqueeView);

        List<String> info = new ArrayList<>();
        info.add("欢迎访问京东app");
        info.add("大家有没有在 听课");
        info.add("是不是还有人在睡觉");
        info.add("你妈妈在旁边看着呢");
        info.add("赶紧的好好学习吧 马上毕业了");
        info.add("你没有事件睡觉了");

        marqueeView.startWithList(info);


    }

    @Override
    public void responseFLMsg(View header_view, ShouYeFLBean shouYeFLBean) {


        recycler_view = header_view.findViewById(R.id.recycler_view);
        List<ShouYeFLBean.DataBean> data = shouYeFLBean.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(gridLayoutManager);
        SYFLAdapter syflAdapter = new SYFLAdapter(R.layout.layout_shouyefenlei, data);
        recycler_view.setAdapter(syflAdapter);

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


    @OnClick({R.id.zxing, R.id.sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zxing:
                new IntentIntegrator(getActivity()).initiateScan();
                break;
            case R.id.sousuo:
                Intent intent = new Intent(getActivity(), SouSuoActivity.class);
                startActivity(intent);
                break;
        }
    }
}

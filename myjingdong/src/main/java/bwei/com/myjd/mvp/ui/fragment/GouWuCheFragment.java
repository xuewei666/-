package bwei.com.myjd.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import bwei.com.myjd.R;
import bwei.com.myjd.di.component.DaggerGouWuCheComponent;
import bwei.com.myjd.di.module.GouWuCheModule;
import bwei.com.myjd.mvp.contract.GouWuCheContract;
import bwei.com.myjd.mvp.data.DingDanBean;
import bwei.com.myjd.mvp.data.GongGongBean;
import bwei.com.myjd.mvp.data.ShippingBean;
import bwei.com.myjd.mvp.presenter.GouWuChePresenter;
import bwei.com.myjd.mvp.ui.activity.DingDanActivity;
import bwei.com.myjd.mvp.ui.activity.LoginActivitysActivity;
import bwei.com.myjd.mvp.ui.activity.MainActivity;
import bwei.com.myjd.mvp.ui.adapter.MyGWCAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class GouWuCheFragment extends BaseFragment<GouWuChePresenter> implements GouWuCheContract.View {

    @BindView(R.id.tb_head)
    Toolbar tbHead;
    @BindView(R.id.elc_show_main)
    ExpandableListView elcShowMain;
    @BindView(R.id.cb_allCheck_main)
    CheckBox cbAllCheckMain;
    @BindView(R.id.btn_allPrice_main)
    TextView btnAllPriceMain;
    @BindView(R.id.btn_allNumber_main)
    Button btnAllNumberMain;
    Unbinder unbinder;
    @BindView(R.id.tv_xsyc)
    TextView tvXsyc;
    Unbinder unbinder1;
    private MyGWCAdapter myGWCAdapter;
    private String uid;
    private List<ShippingBean.DataBean> data;
    private DingDanBean listBean;


    public static GouWuCheFragment newInstance() {
        GouWuCheFragment fragment = new GouWuCheFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerGouWuCheComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .gouWuCheModule(new GouWuCheModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_gou_wu_che, container, false);


    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//
//
//    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        mPresenter.getGUCData(uid);
//    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        tvXsyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivitysActivity.class);
                startActivity(intent);
            }
        });

        if (uid == null) {
            tvXsyc.setVisibility(View.VISIBLE);//显示
            elcShowMain.setVisibility(View.GONE);//隐藏
        }else{
            elcShowMain.setVisibility(View.VISIBLE);//显示
            tvXsyc.setVisibility(View.GONE);//隐藏

        }

        ((AppCompatActivity) getActivity()).setSupportActionBar(tbHead);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setTitle("购物车");

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(GongGongBean<String> event) {
        uid = event.getValue();

        Toast.makeText(getActivity(), uid, Toast.LENGTH_SHORT).show();

        if (mPresenter != null) {

            mPresenter.getGUCData(uid);
        }

    }

    @Override
    public void setData(@Nullable Object data) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销订阅者
        EventBus.getDefault().unregister(this);
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

    private void refreshAllSelectedAndTotalPriceAndTotalNumber() {

        boolean allProductsSelected = myGWCAdapter.isAllProductsSelected();
        cbAllCheckMain.setChecked(allProductsSelected);
//计算总金额
        Double totalPrice = myGWCAdapter.calculateTotalPrice();
        btnAllPriceMain.setText("总价：￥" + totalPrice);
        //计算总数量
        int totalNumber = myGWCAdapter.calculateTotalNumber();
        btnAllNumberMain.setText("去结算(" + totalNumber + ")");
    }

    @Override
    public void responseGUCData(ShippingBean shippingBean) {
        data = shippingBean.getData();
        if (data != null) {
            myGWCAdapter = new MyGWCAdapter(data);
            elcShowMain.setAdapter(myGWCAdapter);


        }

        myGWCAdapter.setOnCartListChangeListener(new MyGWCAdapter.OnCartListChangeListener() {
            @Override
            public void SellerSelectedChange(int groupPosition) {
                //先得到 checkbox
                boolean b = myGWCAdapter.isCurrentSellerAllProductSelected(groupPosition);
                //改变所有当前商家的选中状态
                myGWCAdapter.changeCurrentSellerAllProductSelected(groupPosition, !b);
                myGWCAdapter.notifyDataSetChanged();
                refreshAllSelectedAndTotalPriceAndTotalNumber();
            }

            @Override
            public void changeCurrentProductSelected(int groupPosition, int childPosition) {
                myGWCAdapter.changeCurrentProductSelected(groupPosition, childPosition);
                myGWCAdapter.notifyDataSetChanged();
                refreshAllSelectedAndTotalPriceAndTotalNumber();
            }

            @Override
            public void ProductNumberChange(int groupPosition, int childPosition, int number) {
                myGWCAdapter.changeCurrentProductNumber(groupPosition, childPosition, number);
                myGWCAdapter.notifyDataSetChanged();
                //刷新底部的方法
                refreshAllSelectedAndTotalPriceAndTotalNumber();
            }
        });
        elcShowMain.setGroupIndicator(null);

        for (int i = 0; i < data.size(); i++) {
            elcShowMain.expandGroup(i);

        }
    }


    @OnClick({R.id.cb_allCheck_main, R.id.btn_allNumber_main})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.cb_allCheck_main:
                boolean allProductsSelected = myGWCAdapter.isAllProductsSelected();
                myGWCAdapter.changeAllProductsSelected(!allProductsSelected);
                myGWCAdapter.notifyDataSetChanged();
                //刷新底部的方法
                refreshAllSelectedAndTotalPriceAndTotalNumber();
                break;

            case R.id.btn_allNumber_main:

                dingdan();

                break;
        }
    }

    public void dingdan() {
        List<DingDanBean> list1 = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            List<ShippingBean.DataBean.ListBean> list = data.get(i).getList();


          //  String abc = dingDanBean.getAbc();
            for (int j = 0; j < list.size(); j++) {
                //只有选中采取传值
                if (list.get(j).getSelected() == 1) {

                    String title = list.get(j).getTitle();
                    double price = list.get(j).getPrice();
                    String images = list.get(j).getImages();

                    String[] split = images.split("\\|");
                    listBean = new DingDanBean(price, split[0], title);

                    list1.add(listBean);




                }

            }

        }
        //EventBus.getDefault().postSticky(listBean);
        Intent intent = new Intent(getActivity(), DingDanActivity.class);
        intent.putExtra("list", (Serializable) list1);
        startActivity(intent);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            if (uid != null) {
                mPresenter.getGUCData(uid);
                elcShowMain.setVisibility(View.VISIBLE);//显示
                tvXsyc.setVisibility(View.GONE);//隐藏
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}

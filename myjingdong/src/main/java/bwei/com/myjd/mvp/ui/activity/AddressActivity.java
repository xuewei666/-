package bwei.com.myjd.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.myjd.R;

import bwei.com.myjd.di.component.DaggerAddressComponent;
import bwei.com.myjd.di.module.AddressModule;
import bwei.com.myjd.mvp.contract.AddressContract;
import bwei.com.myjd.mvp.data.DiZhiBean;
import bwei.com.myjd.mvp.data.GongGongBean;
import bwei.com.myjd.mvp.presenter.AddressPresenter;
import bwei.com.myjd.mvp.ui.adapter.DiZhiAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class AddressActivity extends BaseActivity<AddressPresenter> implements AddressContract.View {

    @BindView(R.id.add_iamge)
    ImageView addIamge;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    @BindView(R.id.btn_addAddress_address)
    Button btnAddAddressAddress;
    private String uid;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAddressComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addressModule(new AddressModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_address; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(GongGongBean<String> event) {
        uid = event.getValue();

        // Toast.makeText(getActivity(), uid, Toast.LENGTH_SHORT).show();

        if (mPresenter != null) {

            mPresenter.getData(uid);
        }

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
        finish();
    }

    @Override
    public void responseMag(DiZhiBean diZhiBean) {
        List<DiZhiBean.DataBean> data = diZhiBean.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddressActivity.this, LinearLayoutManager.VERTICAL, false);
        rvAddress.setLayoutManager(linearLayoutManager);
        DiZhiAdapter diZhiAdapter = new DiZhiAdapter(R.layout.recycler_view_address_list_item, data);
        rvAddress.setAdapter(diZhiAdapter);

        diZhiAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String name = data.get(position).getName();
                long mobile = data.get(position).getMobile();
                String string = String.valueOf(mobile);
                String addr = data.get(position).getAddr();
                int addrid = data.get(position).getAddrid();
                String saddrid = String.valueOf(addrid);
                Intent intent = new Intent(AddressActivity.this, UpdataActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("string",string);
                intent.putExtra("addr",addr);
                intent.putExtra("saddrid",saddrid);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add_iamge, R.id.btn_addAddress_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_iamge:
                finish();
                break;
            case R.id.btn_addAddress_address:
                Intent intent = new Intent(AddressActivity.this, AddActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getData(uid);
    }
}

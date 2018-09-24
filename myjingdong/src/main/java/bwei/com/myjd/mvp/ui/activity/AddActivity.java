package bwei.com.myjd.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.myjd.R;
import bwei.com.myjd.di.component.DaggerAddComponent;
import bwei.com.myjd.di.module.AddModule;
import bwei.com.myjd.mvp.contract.AddContract;
import bwei.com.myjd.mvp.data.GongGongBean;
import bwei.com.myjd.mvp.data.TJDZBean;
import bwei.com.myjd.mvp.presenter.AddPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class AddActivity extends BaseActivity<AddPresenter> implements AddContract.View {



    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAddComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addModule(new AddModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_add; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销订阅者
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(GongGongBean<String> event) {
        uid = event.getValue();

        // Toast.makeText(getActivity(), uid, Toast.LENGTH_SHORT).show();


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @BindView(R.id.add_add_iamge)
    ImageView addAddIamge;
    @BindView(R.id.et_name_add_address)
    EditText etNameAddAddress;
    @BindView(R.id.et_mobile_add_address)
    EditText etMobileAddAddress;
    @BindView(R.id.et_address_add_address)
    EditText etAddressAddAddress;
    @BindView(R.id.btn_submit_add_address)
    Button btnSubmitAddAddress;
    private String uid;
    @OnClick({R.id.add_add_iamge, R.id.btn_submit_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_add_iamge:
                finish();
                break;
            case R.id.btn_submit_add_address:
                String name = etNameAddAddress.getText().toString();
                String mobile = etMobileAddAddress.getText().toString();
                String address = etAddressAddAddress.getText().toString();
                Map<String,String> map = new HashMap<>();
                map.put("uid",uid);
                map.put("addr",address);
                map.put("mobile",mobile);
                map.put("name",name);
                mPresenter.getData(map);
                break;
        }
    }

    @Override
    public void responseMag(TJDZBean tjdzBean) {
        String msg = tjdzBean.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddActivity.this, AddressActivity.class);
        startActivity(intent);

    }
}

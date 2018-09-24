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
import bwei.com.myjd.di.component.DaggerUpdataComponent;
import bwei.com.myjd.di.module.UpdataModule;
import bwei.com.myjd.mvp.contract.UpdataContract;
import bwei.com.myjd.mvp.data.GongGongBean;
import bwei.com.myjd.mvp.data.UpdataBean;
import bwei.com.myjd.mvp.presenter.UpdataPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class UpdataActivity extends BaseActivity<UpdataPresenter> implements UpdataContract.View {

    @BindView(R.id.updata_iamge)
    ImageView updataIamge;
    @BindView(R.id.et_name_updata_address)
    EditText etNameUpdataAddress;
    @BindView(R.id.et_mobile_updata_address)
    EditText etMobileUpdataAddress;
    @BindView(R.id.et_address_updata_address)
    EditText etAddressUpdataAddress;
    @BindView(R.id.btn_submit_updata_address)
    Button btnSubmitUpdataAddress;
    private String uid;
    private String saddrid;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerUpdataComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .updataModule(new UpdataModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_updata; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String string = intent.getStringExtra("string");
        String addr = intent.getStringExtra("addr");
        saddrid = intent.getStringExtra("saddrid");
        etNameUpdataAddress.setText(name);
        etMobileUpdataAddress.setText(string);
        etAddressUpdataAddress.setText(addr);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(GongGongBean<String> event) {
        uid = event.getValue();

        // Toast.makeText(getActivity(), uid, Toast.LENGTH_SHORT).show();


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.updata_iamge, R.id.btn_submit_updata_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.updata_iamge:
                finish();
                break;
            case R.id.btn_submit_updata_address:
                String name = etNameUpdataAddress.getText().toString();
                String mobile = etMobileUpdataAddress.getText().toString();
                String address = etAddressUpdataAddress.getText().toString();


                Map<String,Object> map = new HashMap<>();
                Integer Iuid = Integer.valueOf(uid);
                map.put("uid",Iuid);
                Integer integer = Integer.valueOf(saddrid);
                map.put("addrid",integer);
                map.put("addr",address);


                Integer Imobile = Integer.valueOf(mobile);
                map.put("mobile",Imobile);
                map.put("name",name);
                mPresenter.getData(map);
                break;
        }
    }

    @Override
    public void responseMag(UpdataBean updataBean) {

        String msg = updataBean.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();

    }
}

package bwei.com.myjd.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;


import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.myjd.R;
import bwei.com.myjd.di.component.DaggerLoginActivitysComponent;
import bwei.com.myjd.di.module.LoginActivitysModule;
import bwei.com.myjd.mvp.contract.LoginActivitysContract;
import bwei.com.myjd.mvp.data.GongGongBean;
import bwei.com.myjd.mvp.data.LoginBean;
import bwei.com.myjd.mvp.presenter.LoginActivitysPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class LoginActivitysActivity extends BaseActivity<LoginActivitysPresenter> implements LoginActivitysContract.View {

    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;
    @BindView(R.id.login_input_name)
    EditText loginInputName;
    @BindView(R.id.login_input_pwd)
    EditText loginInputPwd;
    @BindView(R.id.login_input_denglu)
    Button loginInputDenglu;
    @BindView(R.id.login_input_register)
    TextView loginInputRegister;
    @BindView(R.id.qq)
    Button qq;


    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(LoginActivitysActivity.this, "成功了", Toast.LENGTH_LONG).show();


            String uid = "71";
            GongGongBean<String> gongBean = new GongGongBean<>();
            gongBean.setValue(uid);

            EventBus.getDefault().postSticky(gongBean);



            myname = data.get("name");
            iconyrl = data.get("iconurl");
            Intent intent2 = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("myname", myname);
            bundle.putString("iconyrl", iconyrl);
            intent2.putExtras(bundle);
            setResult(888, intent2);
            finish();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivitysActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivitysActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    private String myname;
    private String iconyrl;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginActivitysComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginActivitysModule(new LoginActivitysModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }

        setSupportActionBar(homeToolbar);
        setTitle("登录");
        homeToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

    }

    @OnClick({R.id.qq,R.id.home_toolbar, R.id.login_input_name, R.id.login_input_pwd, R.id.login_input_denglu, R.id.login_input_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_input_denglu:
                String name = loginInputName.getText().toString();
                String password = loginInputPwd.getText().toString();
                Map<String, String> map = new HashMap<>();
                map.put("mobile", name);
                map.put("password", password);
                mPresenter.getData(map);

                break;
            case R.id.login_input_register:
                // mPresenter.getRegister();
                Intent intent = new Intent(LoginActivitysActivity.this, RegisterActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, 999);
                break;
            case R.id.qq:

                UMShareAPI.get(this).getPlatformInfo(LoginActivitysActivity.this, SHARE_MEDIA.QQ, authListener);



                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            String name = data.getExtras().getString("name");
            String password = data.getExtras().getString("password");
            loginInputName.setText(name);
            loginInputPwd.setText(password);

        }
    }


    @Override
    public void responseLoginData(LoginBean loginBean) {
        String code = loginBean.getCode();
        String msg = loginBean.getMsg();


        if (code.equals("0")) {

            String mobile = loginBean.getData().getMobile();

            int uid = loginBean.getData().getUid();

            GongGongBean<String> gongBean = new GongGongBean<>();

            gongBean.setValue(uid+"");

            EventBus.getDefault().postSticky(gongBean);

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("mobile", mobile);
            intent.putExtras(bundle);
            setResult(1, intent);
            finish();


        }else{

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        }

    }


}

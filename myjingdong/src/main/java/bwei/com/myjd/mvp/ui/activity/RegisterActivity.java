package bwei.com.myjd.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.myjd.R;
import bwei.com.myjd.di.component.DaggerRegisterComponent;
import bwei.com.myjd.di.module.RegisterModule;
import bwei.com.myjd.mvp.contract.RegisterContract;
import bwei.com.myjd.mvp.data.RegisterBean;
import bwei.com.myjd.mvp.presenter.RegisterPresenter;
import bwei.com.myjd.mvp.ui.fragment.WoDeFragment;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.register_input_name)
    EditText registerInputName;
    @BindView(R.id.register_input_pwd)
    EditText registerInputPwd;
    @BindView(R.id.register_input_btn)
    Button registerInputBtn;
    private String name;
    private String password;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerRegisterComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .registerModule(new RegisterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_register; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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

    @OnClick(R.id.register_input_btn)
    public void onViewClicked() {
        name = registerInputName.getText().toString();
        password = registerInputPwd.getText().toString();
        Map<String,String> map = new HashMap<>();
        map.put("mobile", name);
        map.put("password", password);
        mPresenter.getRegister(map);


    }

    @Override
    public void responseRegisterData(RegisterBean registerBean) {
        String code = registerBean.getCode();
        String msg = registerBean.getMsg();
        if (code.equals("0")){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("name",name);
            bundle.putString("password",password);
            intent.putExtras(bundle);
            setResult(1,intent);
            finish();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

package bwei.com.myjd.mvp.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import bwei.com.myjd.R;
import bwei.com.myjd.di.component.DaggerWoDeComponent;
import bwei.com.myjd.di.module.WoDeModule;
import bwei.com.myjd.mvp.contract.WoDeContract;
import bwei.com.myjd.mvp.data.GongGongBean;
import bwei.com.myjd.mvp.presenter.WoDePresenter;
import bwei.com.myjd.mvp.ui.activity.LoginActivitysActivity;
import bwei.com.myjd.mvp.ui.activity.SettingActivity;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class WoDeFragment extends BaseFragment<WoDePresenter> implements WoDeContract.View {


    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.wd_xiaoxi)
    ImageView wdXiaoxi;
    @BindView(R.id.shezhi)
    ImageView shezhi;
    Unbinder unbinder;
    @BindView(R.id.wode_image)
    SimpleDraweeView wodeImage;
    private String uid;

    public static WoDeFragment newInstance() {
        WoDeFragment fragment = new WoDeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerWoDeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .woDeModule(new WoDeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("WoDeFragment", "+++++++++++");
        return inflater.inflate(R.layout.fragment_wo_de, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

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

 /*   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }*/


    @OnClick({R.id.wode_image, R.id.login, R.id.wd_xiaoxi, R.id.shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wode_image:
                break;
            case R.id.login:
                String s = login.getText().toString();
                if (s.equals("登录/注册")){
                    Intent intent = new Intent(getActivity(), LoginActivitysActivity.class);
                    startActivityForResult(intent, 999);
                    break;
                }else{

                    Intent intent = new Intent(getActivity(), SettingActivity.class);
                    intent.putExtra("name",s);
                    startActivityForResult(intent, 999);
                }

            case R.id.wd_xiaoxi:
                break;
            case R.id.shezhi:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == 1) {
            String mobile = data.getExtras().getString("mobile");
            Uri parse = Uri.parse("file:/data/user/0/bwei.com.myjd/files/photo.png");
            wodeImage.setImageURI(parse);
            login.setText(mobile);
        } else


            if (requestCode == 999 && resultCode == 888) {

            String myname = data.getExtras().getString("myname");
            String iconyrl = data.getExtras().getString("iconyrl");
            login.setText(myname);
            //wodeImage
            Uri parse = Uri.parse(iconyrl);
            wodeImage.setImageURI(parse);

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser){
            if(uid!=null){
                Uri parse = Uri.parse("file:/data/user/0/bwei.com.myjd/files/photo.png");
                wodeImage.setImageURI(parse);

            }

        }
    }

}

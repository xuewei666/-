package bwei.com.myjd.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
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
import bwei.com.myjd.di.component.DaggerSPXQComponent;
import bwei.com.myjd.di.module.SPXQModule;
import bwei.com.myjd.mvp.contract.SPXQContract;
import bwei.com.myjd.mvp.data.GongGongBean;
import bwei.com.myjd.mvp.data.SPXQBean;
import bwei.com.myjd.mvp.data.TJGWCBean;
import bwei.com.myjd.mvp.presenter.SPXQPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SPXQActivity extends BaseActivity<SPXQPresenter> implements SPXQContract.View {

    @BindView(R.id.zhan_simple)
    SimpleDraweeView zhanSimple;
    @BindView(R.id.zhan_price)
    TextView zhanPrice;
    @BindView(R.id.zhan_yuanprice)
    TextView zhanYuanprice;
    @BindView(R.id.zhan_tite)
    TextView zhanTite;
    @BindView(R.id.zhan_jia)
    Button zhanJia;
    @BindView(R.id.zhan_gou)
    Button zhanGou;
    private SPXQBean.DataBean data;
    private String pid;
    private String uid;
    Map<String,String> map = new HashMap<>();


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSPXQComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .sPXQModule(new SPXQModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_spxq; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        mPresenter.getData(pid);

        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(GongGongBean<String> event) {
        uid = event.getValue();
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

    @OnClick({R.id.zhan_jia, R.id.zhan_gou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhan_jia:
                if (uid==null){
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();

                }else {
                    map.put("uid",uid);
                    map.put("pid",pid);

                    mPresenter.getTJGWCData(map);


                }


                break;
            case R.id.zhan_gou:
                break;
        }
    }

    @Override
    public void getresponseMag(SPXQBean spxqBean) {
        data = spxqBean.getData();
        String images = data.getImages();
        double price = data.getPrice();
        zhanPrice.setText(price+"");
        String title = data.getTitle();
        zhanTite.setText(title);

        if (images.contains("|")){

            images = images.substring(0, images.indexOf("|"));

        }else {

             images = data.getImages();

        }
        Uri parse = Uri.parse(images);
        zhanSimple.setImageURI(parse);

    }

    @Override
    public void getTJGWCMsg(TJGWCBean spxqBean) {
        String msg = spxqBean.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

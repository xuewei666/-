package bwei.com.myjd.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.myjd.R;
import bwei.com.myjd.di.component.DaggerSouSuo2Component;
import bwei.com.myjd.di.module.SouSuo2Module;
import bwei.com.myjd.mvp.contract.SouSuo2Contract;
import bwei.com.myjd.mvp.data.SouSuoBean;
import bwei.com.myjd.mvp.presenter.SouSuo2Presenter;
import bwei.com.myjd.mvp.ui.adapter.SouSuoAdapter;
import bwei.com.myjd.mvp.ui.adapter.SouSuoAdapter2;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SouSuo2Activity extends BaseActivity<SouSuo2Presenter> implements SouSuo2Contract.View {

    @BindView(R.id.iv_fanhui2)
    ImageView ivFanhui2;
    @BindView(R.id.et_txt2)
    EditText etTxt2;
    @BindView(R.id.tv_sousuo2)
    ImageView tvSousuo2;
    @BindView(R.id.sousuo_recyler)
    RecyclerView sousuoRecyler;
    @BindView(R.id.sousuo_recyler2)
    RecyclerView sousuoRecyler2;

    private String sort = "0";
    private int aaa = 2;
    private List<SouSuoBean.DataBean> data;
    private Map<String, String> map;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSouSuo2Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .souSuo2Module(new SouSuo2Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_sou_suo2;


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        etTxt2.setText(name);
        map = new HashMap<>();
        map.put("keywords", name);
        map.put("sort", sort);
        mPresenter.getData(map);
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

    @OnClick({R.id.iv_fanhui2, R.id.sousuo_recyler, R.id.tv_sousuo2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui2:
                finish();
                break;
            case R.id.tv_sousuo2:
                aaa++;
                mPresenter.getData(map);
                break;
        }
    }

    @Override
    public void responseMsg(SouSuoBean souSuoBean) {
        data = souSuoBean.getData();
        //创建适配器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SouSuo2Activity.this, LinearLayoutManager.VERTICAL, false);
        sousuoRecyler.setLayoutManager(linearLayoutManager);
        SouSuoAdapter souSuoAdapter = new SouSuoAdapter(R.layout.layout_sousuo_h, data);
        sousuoRecyler.setAdapter(souSuoAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(SouSuo2Activity.this, 2, GridLayoutManager.VERTICAL,false);
        sousuoRecyler2.setLayoutManager(gridLayoutManager);
        SouSuoAdapter2 souSuoAdapter2 = new SouSuoAdapter2(R.layout.layout_sousuo_v, data);
        sousuoRecyler2.setAdapter(souSuoAdapter2);
        if (aaa % 2 == 0) {
            sousuoRecyler.setVisibility(View.VISIBLE);//显示
            sousuoRecyler2.setVisibility(View.GONE);//隐藏

        } else {

            sousuoRecyler2.setVisibility(View.VISIBLE);//显示
            sousuoRecyler.setVisibility(View.GONE);//隐藏
        }
    }


}

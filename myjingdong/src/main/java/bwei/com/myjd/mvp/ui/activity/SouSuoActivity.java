package bwei.com.myjd.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyales.tagcloud.library.TagBaseAdapter;
import com.fyales.tagcloud.library.TagCloudLayout;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.myjd.R;
import bwei.com.myjd.di.component.DaggerSouSuoComponent;
import bwei.com.myjd.di.module.SouSuoModule;
import bwei.com.myjd.mvp.contract.SouSuoContract;
import bwei.com.myjd.mvp.presenter.SouSuoPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SouSuoActivity extends BaseActivity<SouSuoPresenter> implements SouSuoContract.View {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.et_txt)
    EditText etTxt;
    @BindView(R.id.tv_sousuo)
    TextView tvSousuo;
    @BindView(R.id.container)
    TagCloudLayout container;
    @BindView(R.id.qingchu)
    Button qingchu;
    private ArrayList mList;
    private TagBaseAdapter mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSouSuoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .souSuoModule(new SouSuoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_sou_suo; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mList = new ArrayList<>();
        mList.add("苹果");
        mList.add("香蕉");
        mList.add("炸鸡块");
        mList.add("超级无敌大鸡排");
        mAdapter = new TagBaseAdapter(this, mList);
        container.setAdapter(mAdapter);
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

    @OnClick({R.id.iv_fanhui, R.id.et_txt, R.id.tv_sousuo, R.id.container, R.id.qingchu})
    public void onViewClicked(View view) {

        switch (view.getId()) {


            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.et_txt:
                break;
            case R.id.tv_sousuo:
                String s = etTxt.getText().toString();
                mList.add(s);
                mAdapter.notifyDataSetChanged();
                Intent intent = new Intent(SouSuoActivity.this, SouSuo2Activity.class);
                intent.putExtra("name",s);
                startActivity(intent);

                break;
            case R.id.qingchu:
                for (int i = 0; i <mList.size() ; i++) {
                    for (int j = 0; j <mList.size() ; j++) {
                        mList.remove(j);
                    }
                }
               //       mList.clear();
                mAdapter.notifyDataSetChanged();
                break;

        }

    }
}

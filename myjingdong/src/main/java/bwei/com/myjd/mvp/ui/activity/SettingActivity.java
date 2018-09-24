package bwei.com.myjd.mvp.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.myjd.BuildConfig;
import bwei.com.myjd.R;
import bwei.com.myjd.di.component.DaggerSettingComponent;
import bwei.com.myjd.di.module.SettingModule;
import bwei.com.myjd.mvp.contract.SettingContract;
import bwei.com.myjd.mvp.presenter.SettingPresenter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.View {
    private String path = Environment.getExternalStorageDirectory() + "/photo.png";
    @BindView(R.id.set_iamge)
    ImageView setIamge;
    @BindView(R.id.iv_img_setting)
    ImageView ivImgSetting;
    @BindView(R.id.ll_userIcon_setting)
    LinearLayout llUserIconSetting;
    @BindView(R.id.tv_userName_setting)
    TextView tvUserNameSetting;
    @BindView(R.id.ll_userName_setting)
    LinearLayout llUserNameSetting;
    @BindView(R.id.tv_nickName_setting)
    TextView tvNickNameSetting;
    @BindView(R.id.ll_nickName_setting)
    LinearLayout llNickNameSetting;
    @BindView(R.id.tv_gender_setting)
    TextView tvGenderSetting;
    @BindView(R.id.ll_gender_setting)
    LinearLayout llGenderSetting;
    @BindView(R.id.ll_userBirthday_setting)
    LinearLayout llUserBirthdaySetting;
    @BindView(R.id.ll_address_setting)
    LinearLayout llAddressSetting;
    @BindView(R.id.btn_cancel_setting)
    Button btnCancelSetting;
    private PopupWindow pw;
    private TextView camera;
    private TextView pick;
    private TextView cancel;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSettingComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .settingModule(new SettingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_setting; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();

        String username = intent.getStringExtra("name");
        tvUserNameSetting.setText(username);

        //加载PopupWindow的布局
        View popupView = View.inflate(this, R.layout.popup_window_item, null);
        //另一种新建方式
        pw = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //点击其他地方关闭
        pw.setOutsideTouchable(true);
        //给PopupWindow设置透明背景色
        pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        camera = popupView.findViewById(R.id.tv_camera_popup);
        pick = popupView.findViewById(R.id.tv_pick_popup);
        cancel = popupView.findViewById(R.id.tv_cancel_popup);
        //相机
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //打开相机MediaStore.ACTION_IMAGE_CAPTURE
       Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(intent, 100);
                pw.dismiss();
            }
        });
        //相册
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开相册
                /*Intent intent = new Intent(Intent.ACTION_PICK);
                //设置图片格式
                intent.setType("image/*");
                startActivityForResult(intent, 200);*/
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 200);
                pw.dismiss();
            }
        });
        //取消
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
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

    @OnClick({R.id.set_iamge, R.id.iv_img_setting, R.id.ll_userIcon_setting, R.id.tv_userName_setting, R.id.ll_userName_setting, R.id.tv_nickName_setting, R.id.ll_nickName_setting, R.id.tv_gender_setting, R.id.ll_gender_setting, R.id.ll_userBirthday_setting, R.id.ll_address_setting, R.id.btn_cancel_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_iamge:
                finish();
                break;
            case R.id.iv_img_setting:
                break;
            case R.id.ll_userIcon_setting:
                pw.showAtLocation(llUserIconSetting, Gravity.BOTTOM, 0, 0);
                break;

            case R.id.ll_userName_setting:
                Toast.makeText(SettingActivity.this, "用户名暂不支持修改噢~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_nickName_setting:
                break;
            case R.id.ll_nickName_setting:
                String nickName = (String) tvNickNameSetting.getText();
                View view1 = View.inflate(this, R.layout.alert_dialog_view, null);
                final EditText editText = view1.findViewById(R.id.et_dialog_view);
                editText.setText(nickName);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setIcon(R.mipmap.ic_launcher);
                builder1.setTitle("输入框");
                builder1.setMessage("请输入你要修改的昵称：\n");
                builder1.setView(view1);
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newNickName = editText.getText().toString();
                        tvNickNameSetting.setText(newNickName);


                    }
                });

                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder1.create().show();

                break;
            case R.id.tv_gender_setting:
                break;
            case R.id.ll_gender_setting:
                Toast.makeText(SettingActivity.this, "性别暂不支持修改噢~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_userBirthday_setting:
                Toast.makeText(SettingActivity.this, "出生日期暂不支持修改噢~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_address_setting:

                Intent intent = new Intent(SettingActivity.this, AddressActivity.class);
                startActivityForResult(intent, 1);



                break;
            case R.id.btn_cancel_setting:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 相机调用裁剪功能
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到拍完的照片进行裁剪
            it.setDataAndType(Uri.fromFile(new File(path)), "image/*");
            //设置是否支持裁剪
            it.putExtra("crop", true);
            //设置框的宽高比
            it.putExtra("aspactX", 1);
            it.putExtra("aspactY", 1);
            //设置输出的照片
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //将图片返回
            it.putExtra("return-data", true);

            startActivityForResult(it, 300);

        }

        //设置裁剪
        if (requestCode == 200 && resultCode == RESULT_OK) {
            //得到图片路径
            Uri uri = data.getData();
            //调用系统裁剪功能
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到照片进行裁剪
            it.setDataAndType(uri, "image/*");
            //设置是否支持裁剪
            it.putExtra("crop", true);
            //设置框的宽高比
            it.putExtra("aspactX", 1);
            it.putExtra("aspactY", 1);
            //设置输出图片大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //将图片返回
            it.putExtra("return-data", true);

            startActivityForResult(it, 300);
        }
        //裁剪完后回到设置图片
        if (requestCode == 300 && resultCode == RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            //获取文件路径
            File file = new File(getFilesDir().getAbsolutePath());
            if (!file.exists()) {
                //如果路径不存在就创建
                file.mkdirs();
            }
            //创建文件
            File file1 = new File(file, "photo.png");
            FileOutputStream fileOutputStream;
            try {
                //文件输出流
                fileOutputStream = new FileOutputStream(file1);
                //将bitmap写入文件流
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                //刷新此输出流并强制将所有缓冲的输出字节被写出
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //RequestBody封装了文件和文件的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file1);
             Log.e("SettingActivity", "file1:" + file1);
            // MultipartBody.Part封装了接受的key和文件名字和RequestBody
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file1.getName(), requestBody);

            //调用上传头像
            //presenter.uploadPhoto(uid, part);
        }

    }

}

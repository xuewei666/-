package bwei.com.myjd.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwei.com.myjd.R;

public class ActivitySY extends AppCompatActivity {

    @BindView(R.id.text_view)
    TextView textView;
    private int time = 3;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                if (time>0){

                    textView.setText(time+"s");
                    time--;
                    handler.sendEmptyMessageDelayed(0,1000);
                }else{
                    Intent intent = new Intent(ActivitySY.this,MainActivity.class);

                    startActivity(intent);
                    finish();

                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy);
        ButterKnife.bind(this);
        handler.sendEmptyMessageDelayed(0,1000);
    }

    @OnClick(R.id.text_view)
    public void onViewClicked() {
        Intent intent = new Intent(ActivitySY.this,MainActivity.class);
        startActivity(intent);

        finish();
        handler.removeCallbacksAndMessages(null);
    }
}

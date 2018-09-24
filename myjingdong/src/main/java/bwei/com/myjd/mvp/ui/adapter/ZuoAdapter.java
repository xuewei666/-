package bwei.com.myjd.mvp.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.ShouYeFLBean;

public class ZuoAdapter extends BaseQuickAdapter<ShouYeFLBean.DataBean,BaseViewHolder>{

    public ZuoAdapter(int layoutResId, @Nullable List<ShouYeFLBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShouYeFLBean.DataBean item) {
        helper.setText(R.id.zuo_mz,item.getName())
                .addOnClickListener(R.id.zuo_mz);
        TextView view = helper.getView(R.id.zuo_mz);



    }
}

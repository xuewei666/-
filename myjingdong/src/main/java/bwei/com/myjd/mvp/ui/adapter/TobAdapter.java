package bwei.com.myjd.mvp.ui.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.DingDanBean;

public class TobAdapter extends BaseQuickAdapter<DingDanBean,BaseViewHolder>{
    public TobAdapter(int layoutResId, @Nullable List<DingDanBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DingDanBean item) {
        SimpleDraweeView view = helper.getView(R.id.tb01_image);
        Uri parse = Uri.parse(item.getIconurl());
        view.setImageURI(parse);
        helper.setText(R.id.tob01_title,item.getTitle());
        helper.setText(R.id.tob01_price,item.getPrice()+"");
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        String format = formatter.format(curDate);
        helper.setText(R.id.tob01_data,format);
        helper.setText(R.id.tob01_zhuangtai,item.getZhuangtai());

    }
}

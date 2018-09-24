package bwei.com.myjd.mvp.ui.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.SouSuoBean;

public class SouSuoAdapter extends BaseQuickAdapter<SouSuoBean.DataBean,BaseViewHolder>{
    public SouSuoAdapter(int layoutResId, @Nullable List<SouSuoBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SouSuoBean.DataBean item) {
        helper.setText(R.id.ss_biaoti,item.getTitle());
        helper.setText(R.id.ss_biaoti_jiage,item.getPrice()+"");
        SimpleDraweeView view = helper.getView(R.id.ss_image);
        Uri parse = Uri.parse(item.getImages());
        view.setImageURI(parse);
    }
}

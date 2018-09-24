package bwei.com.myjd.mvp.ui.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.SouSuoBean;

public class SouSuoAdapter2 extends BaseQuickAdapter<SouSuoBean.DataBean,BaseViewHolder>{
    public SouSuoAdapter2(int layoutResId, @Nullable List<SouSuoBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SouSuoBean.DataBean item) {
        helper.setText(R.id.s2_text2,item.getTitle());
        helper.setText(R.id.s2_price2,item.getPrice()+"");
        SimpleDraweeView view = helper.getView(R.id.s2_image2);
        Uri parse = Uri.parse(item.getImages());
        view.setImageURI(parse);
    }
}

package bwei.com.myjd.mvp.ui.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.LunBoUserBean;

public class MyJdmxAdapte extends BaseQuickAdapter<LunBoUserBean.MiaoshaBean.ListBeanX,BaseViewHolder>{
    public MyJdmxAdapte(int layoutResId, @Nullable List<LunBoUserBean.MiaoshaBean.ListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LunBoUserBean.MiaoshaBean.ListBeanX item) {
        helper.setText(R.id.mx_textview,item.getPrice()+"");
       // int position = helper.getLayoutPosition();
        String images = item.getImages();
        String[] split = images.split("\\|");
        SimpleDraweeView view = helper.getView(R.id.mx_imageview);
        Uri parse = Uri.parse(split[2]);
        view.setImageURI(parse);
    }
}

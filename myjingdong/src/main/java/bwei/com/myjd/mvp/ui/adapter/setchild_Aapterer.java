package bwei.com.myjd.mvp.ui.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.FenLeiBean;

class setchild_Aapterer extends BaseQuickAdapter<FenLeiBean.DataBean.ListBean,BaseViewHolder>{


    public setchild_Aapterer(int layoutResId, @Nullable List<FenLeiBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FenLeiBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_child,item.getName());
        String icon = item.getIcon();
       // String[] split = icon.split("\\|");
        SimpleDraweeView view = helper.getView(R.id.img_child);
        Uri parse = Uri.parse(icon);
        view.setImageURI(parse);
    }
}

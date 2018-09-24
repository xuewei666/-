package bwei.com.myjd.mvp.ui.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.ShouYeFLBean;

/**
 * @author JackLee
 */
public class SYFLAdapter extends BaseQuickAdapter<ShouYeFLBean.DataBean,BaseViewHolder> {
    public SYFLAdapter(int layoutResId, @Nullable List<ShouYeFLBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShouYeFLBean.DataBean item) {
        helper.setText(R.id.sy_textview,item.getName());
        SimpleDraweeView view = helper.getView(R.id.sy_imageview);
        if (item.getIcon()!=null){
            Uri parse = Uri.parse(item.getIcon());
            view.setImageURI(parse);
        }

    }
}

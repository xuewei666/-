package bwei.com.myjd.mvp.ui.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URL;
import java.util.List;

import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.LunBoUserBean;

public class MYTJAdapter extends BaseQuickAdapter<LunBoUserBean.TuijianBean.ListBean,BaseViewHolder>{
    public MYTJAdapter(int layoutResId, @Nullable List<LunBoUserBean.TuijianBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LunBoUserBean.TuijianBean.ListBean item) {
        helper.setText(R.id.tj_text,item.getTitle());
        helper.setText(R.id.tj_price,item.getPrice()+"");
        String images = item.getImages();
        if (images.contains("|")){

            images = images.substring(0, images.indexOf("|"));

        }else {

            images= item.getImages();
        }

        SimpleDraweeView view = helper.getView(R.id.tj_image);
        if (images!=null){
            Uri parse = Uri.parse(images);
            view.setImageURI(parse);

        }
        helper.addOnClickListener(R.id.tj_image);
        helper.addOnClickListener(R.id.tj_text);

    }
}

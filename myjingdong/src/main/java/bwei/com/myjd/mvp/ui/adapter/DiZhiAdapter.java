package bwei.com.myjd.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.DiZhiBean;

public class DiZhiAdapter extends BaseQuickAdapter<DiZhiBean.DataBean,BaseViewHolder>{
    public DiZhiAdapter(int layoutResId, @Nullable List<DiZhiBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiZhiBean.DataBean item) {
        helper.setText(R.id.tv_name_address_item,item.getName());
        long mobile = item.getMobile();
        String s = String.valueOf(mobile);

        String substring1 = s.substring(0, 3);
        String substring2 = s.substring(7, s.length());
        String substring = substring1 + "****" + substring2;
        helper.setText(R.id.tv_mobile_address_item,substring);
        helper.setText(R.id.tv_address_address,item.getAddr());
        helper.addOnClickListener(R.id.ll_edit_address_item);
        CheckBox view = helper.getView(R.id.cb_address_item);
        if (item.getStatus()==1){
            view.setChecked(true);
            helper.setText(R.id.tv_check_address_item,"默认地址");

        }else{
            view.setChecked(false);
            helper.setText(R.id.tv_check_address_item,"设为默认");

        }
    }
}

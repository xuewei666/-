package bwei.com.myjd.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.net.HttpRetryException;
import java.time.Year;
import java.util.List;

import bwei.com.myjd.R;
import bwei.com.myjd.mvp.data.FenLeiBean;

public class SetYouAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<FenLeiBean.DataBean> list;

    public  SetYouAdapter(Context context, List<FenLeiBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View inflate = View.inflate(context, R.layout.layout_fuqin, null);

        TextView fu_text = inflate.findViewById(R.id.fu_text);
        fu_text.setText(list.get(groupPosition).getName());
        return inflate;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View inflate = View.inflate(context, R.layout.layout_zi, null);
        RecyclerView recycler_zi = inflate.findViewById(R.id.recycler_zi);

        GridLayoutManager gridlayout = new GridLayoutManager(context,3);
        recycler_zi.setLayoutManager(gridlayout);
        List<FenLeiBean.DataBean.ListBean> list = this.list.get(groupPosition).getList();
        setchild_Aapterer setchild_aapterer = new setchild_Aapterer(R.layout.layout_haizi, list);
        recycler_zi.setAdapter(setchild_aapterer);


        return inflate;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

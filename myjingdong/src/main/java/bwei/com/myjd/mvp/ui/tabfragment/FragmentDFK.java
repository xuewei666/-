package bwei.com.myjd.mvp.ui.tabfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bwei.com.myjd.R;


/**
 * Created by Jack Lee on 2018/7/25.
 */

public class FragmentDFK extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_tob02, container, false);
        return inflate;
    }
}

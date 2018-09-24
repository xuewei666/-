package bwei.com.myjd.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import bwei.com.myjd.mvp.data.LunBoUserBean;
import bwei.com.myjd.mvp.data.ShouYeFLBean;
import io.reactivex.Observable;


public interface HomeContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void  responseMsg(LunBoUserBean lunBoUserBean);
        void  error(String s);
        void  responseFLMsg(android.view.View header_view, ShouYeFLBean shouYeFLBean);

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<LunBoUserBean> responseData();
        Observable<ShouYeFLBean> responseFLData();


    }
}

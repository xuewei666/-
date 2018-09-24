package bwei.com.myjd.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.Map;

import bwei.com.myjd.mvp.data.TJDZBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.AddContract;


@ActivityScope
public class AddPresenter extends BasePresenter<AddContract.Model, AddContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public AddPresenter(AddContract.Model model, AddContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
   public  void getData(Map<String, String> map){
           Observable<TJDZBean> observable = mModel.requestData(map);
           observable.subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Consumer<TJDZBean>() {
                       @Override
                       public void accept(TJDZBean tjdzBean) throws Exception {
                           mRootView.responseMag(tjdzBean);
                       }
                   });
       }
}

package bwei.com.myjd.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.Map;

import bwei.com.myjd.mvp.data.SPXQBean;
import bwei.com.myjd.mvp.data.TJGWCBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.SPXQContract;


@ActivityScope
public class SPXQPresenter extends BasePresenter<SPXQContract.Model, SPXQContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public SPXQPresenter(SPXQContract.Model model, SPXQContract.View rootView) {
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
    public void getData(String pid){
        Observable<SPXQBean> observable = mModel.requestData(pid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SPXQBean>() {
                    @Override
                    public void accept(SPXQBean spxqBean) throws Exception {
                        mRootView.getresponseMag(spxqBean);
                    }
                });

    }
    public void getTJGWCData(Map<String, String> pid){
        Observable<TJGWCBean> observable = mModel.requestTJGWCData(pid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TJGWCBean>() {
                    @Override
                    public void accept(TJGWCBean spxqBean) throws Exception {
                        mRootView.getTJGWCMsg(spxqBean);
                    }
                });

    }
}

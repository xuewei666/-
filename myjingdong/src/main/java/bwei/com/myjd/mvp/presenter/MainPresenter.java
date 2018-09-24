package bwei.com.myjd.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import bwei.com.myjd.mvp.data.LunBoUserBean;
import bwei.com.myjd.mvp.data.ShouYeFLBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.MainContract;


@FragmentScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
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

    //轮播图
    @SuppressLint("CheckResult")
    public void  getData(){
        Observable<LunBoUserBean> observable = mModel.responseData();
        observable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<LunBoUserBean>() {
                                 @Override
                                 public void accept(LunBoUserBean lunBoUserBean) throws Exception {
                                     mRootView.responseMsg(lunBoUserBean);

                                 }
                             }, new Consumer<Throwable>() {
                                 @Override
                                 public void accept(Throwable throwable) throws Exception {
                                        mRootView.error(throwable.toString());
                                 }
                             }

                  );

    }

    public  void getFLData(){
        Observable<ShouYeFLBean> observable = mModel.responseFLData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShouYeFLBean>() {
                    @Override
                    public void accept(ShouYeFLBean shouYeFLBean) throws Exception {

                            mRootView.responseFLMsg(shouYeFLBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mRootView.error(throwable.toString());
                    }
                });


    }
}



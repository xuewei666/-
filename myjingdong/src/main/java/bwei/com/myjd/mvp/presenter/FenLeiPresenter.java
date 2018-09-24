package bwei.com.myjd.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import bwei.com.myjd.mvp.data.FenLeiBean;
import bwei.com.myjd.mvp.data.ShouYeFLBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import bwei.com.myjd.mvp.contract.FenLeiContract;


@FragmentScope
public class FenLeiPresenter extends BasePresenter<FenLeiContract.Model, FenLeiContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public FenLeiPresenter(FenLeiContract.Model model, FenLeiContract.View rootView) {
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
    @SuppressLint("CheckResult")
    public  void  getZuoData(){
        Observable<ShouYeFLBean> observable = mModel.zuoData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShouYeFLBean>() {
                    @Override
                    public void accept(ShouYeFLBean shouYeFLBean) throws Exception {
                        mRootView.responseZuoMsg(shouYeFLBean);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });


    }
    @SuppressLint("CheckResult")
    public  void getYouData(int id){
        Observable<FenLeiBean> observable = mModel.youData(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FenLeiBean>() {
                               @Override
                               public void accept(FenLeiBean fenLeiBean) throws Exception {
                                   mRootView.responseYouMsg(fenLeiBean);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }

                );

    }
}
